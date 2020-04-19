package com.zzd.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zzd.api.dao.TJobMapper;
import com.zzd.api.domain.TDiscuss;
import com.zzd.api.domain.TJob;
import com.zzd.api.domain.TJobExample;
import com.zzd.api.dto.JobDTO;
import com.zzd.api.dto.PageResponseResult;
import com.zzd.api.eunms.EntityStatus;
import com.zzd.api.exceptions.BussException;
import com.zzd.api.service.JobService;
import com.zzd.provider.config.QuartzJobFactory;
import com.zzd.provider.utils.UniqIdUtil;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @date
 * @describe
 */
@Service
public class JobServiceImpl implements JobService {
    private static final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

    @Resource
    private Scheduler scheduler;

    @Resource
    private TJobMapper jobMapper;

    @PostConstruct
    public void init() {
        List<TJob> tJobs = this.listByAll();
        logger.info("初始化任务===================");
        if (tJobs.size() != 0) {
            for (TJob tJob : tJobs) {
                addJob(tJob);
            }
        }
    }

    private void addJob(TJob job) {
        try {
            logger.info("调度任务{}正在启动中......",job.getJobName());
            if (job.getStatus()!=EntityStatus.Valid.getCode()){
                return;
            }
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                Class clazz = QuartzJobFactory.class;
                JobDetail jobDetail = JobBuilder.newJob(clazz)
                        .withIdentity(job.getJobName(), job.getJobGroup())
                        .build();
                jobDetail.getJobDataMap().put("scheduleJob", job);
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
                trigger = TriggerBuilder.
                        newTrigger().
                        withIdentity(job.getJobName(), job.getJobGroup())
                        .withSchedule(scheduleBuilder)
                        .build();
                scheduler.scheduleJob(jobDetail, trigger);
                if (job.getStatus() == EntityStatus.InValid.getCode()) {
                    pauseJob(job);
                }
            } else {
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (Exception e) {
            logger.error("添加任务失败,失败原因:", e);
        }
    }

    private void pauseJob(TJob job) {
        try {
            JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
            scheduler.pauseJob(jobKey);
            logger.info("暂停一个定时任务成功");
        } catch (Exception e) {
            logger.error("暂停任务失败,失败原因是:", e);
        }
    }

    /**
     * 更新时间表达式
     *
     * @param tJob
     */
    public void updateCron(TJob tJob) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(tJob.getJobName(), tJob.getJobGroup());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(tJob.getCronExpression());
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            scheduler.rescheduleJob(triggerKey, trigger);
            logger.info("更新一个定时任务成功");
        } catch (Exception e) {
            logger.error("更新时间表达式失败,失败原因:", e);
        }

    }

    /**
     * 恢复一个定时任务
     *
     * @param
     */
    public void resumeJob(TJob tJob) {
        try {
            //恢复一个定时任务
            JobKey jobKey = JobKey.jobKey(tJob.getJobName(), tJob.getJobGroup());
            scheduler.resumeJob(jobKey);
            logger.info("恢复一个定时任务成功");
        } catch (Exception e) {
            logger.error("恢复定时任务失败,失败原因:", e);
        }
    }


    /**
     * 功能: 移除一个任务
     *
     * @param tJob
     */
    public void removeJob(TJob tJob) {
        try {

            TriggerKey triggerKey = TriggerKey.triggerKey(tJob.getJobName(), tJob.getJobGroup());
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(JobKey.jobKey(tJob.getJobName(), tJob.getJobGroup()));
            logger.info("移除任务成功");
        } catch (Exception e) {
            logger.error("移除任务失败,失败原因是: ", e);
        }
    }

    /**
     * 立即执行一个定时任务
     *
     * @param tJob
     */
    @Override
    public void runOnce(TJob tJob) {
        try {
            JobKey jobKey = JobKey.jobKey(tJob.getJobName(), tJob.getJobGroup());
            scheduler.triggerJob(jobKey);
            logger.info("执行一个定时任务成功");
        } catch (Exception e) {
            logger.error("执行定时任务失败,失败原因:", e);
        }
    }

    private TJob getJob(String id) {
        return jobMapper.selectByPrimaryKey(id);
    }

    private List<TJob> listByAll() {
        TJobExample example = new TJobExample();
        return jobMapper.selectByExample(example);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insert(TJob tJob,String operator) {
        try {
            tJob.setId(UniqIdUtil.getUniqId());
            resetJobInfo(tJob,operator);
            tJob.setStatus((byte) 1);
            jobMapper.insertSelective(tJob);
        } catch (Exception e) {
            logger.error("新增调度任务异常，原因：{}",e);
            throw new BussException("新增调度任务异常");
        }
        this.addJob(tJob);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(TJob tJob,String operator) {
        resetJobInfo(tJob,operator);
        jobMapper.updateByPrimaryKey(tJob);
        updateJob(tJob);
    }

    @Override
    public void updateJob(TJob tJob) {
        TJob job = this.getJob(tJob.getId());
        removeJob(job);
        addJob(job);
    }

    @Override
    public PageResponseResult queryJobList(JobDTO jobDTO) {
        Integer page = jobDTO.getPage();
        if (jobDTO.getPage() == null || jobDTO.getPageSize() == null) {
            throw new BussException("非法入参");
        }
        if (page > 0) {
            jobDTO.setStartNum((page - 1) * jobDTO.getPageSize());
        } else {
            jobDTO.setStartNum(0);
        }
        if(StringUtils.isNotBlank(jobDTO.getSearchInput())){
            jobDTO.setSearchInput("%"+jobDTO.getSearchInput()+"%");
        }

        List<TJob> discussList = jobMapper.selectByJobDTO(jobDTO);
        int total = jobMapper.selectByJobDTOTotal(jobDTO);
        return new PageResponseResult(total, discussList);
    }

    @Override
    public TJob findByJobName(String jobName) {
        TJobExample example = new TJobExample();
        example.createCriteria().andJobNameEqualTo(jobName);
        List<TJob> jobList = jobMapper.selectByExample(example);
        if (jobList==null || jobList.size()>0){
            return null;
        }
        return jobList.get(0);
    }

    @Override
    public TJob findByJobId(String jobId) {
        TJob job = new TJob();
        job = jobMapper.selectByPrimaryKey(jobId);
        return job;
    }

    private void resetJobInfo(TJob job,String operator){
        if (StringUtils.isBlank(job.getCreatedBy())){
            job.setCreatedBy(operator);
            job.setCreateDatetime(new Date());
        }
        job.setUpdatedBy(operator);
        job.setUpdateDatetime(new Date());
    }
}
