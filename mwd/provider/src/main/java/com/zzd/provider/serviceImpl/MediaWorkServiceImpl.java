package com.zzd.provider.serviceImpl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.zzd.api.dao.TMediaWorkMapper;
import com.zzd.api.domain.EmailModel;
import com.zzd.api.domain.TMediaWork;
import com.zzd.api.domain.TMediaWorkExample;
import com.zzd.api.dto.MediaWorkDTO;
import com.zzd.api.dto.PageResponseResult;
import com.zzd.api.eunms.EntityStatus;
import com.zzd.api.exceptions.BussException;
import com.zzd.api.service.MediaWorkService;
import com.zzd.api.service.UnderRankService;
import com.zzd.provider.utils.MailSendUtils;
import com.zzd.provider.utils.RedisUtil;
import com.zzd.provider.utils.UniqIdUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author
 * @date
 * @describe
 */
@Service
public class MediaWorkServiceImpl implements MediaWorkService {
    private Logger logger = LoggerFactory.getLogger(MediaWorkServiceImpl.class);

    @Resource
    private TMediaWorkMapper mediaWorkMapper;

    @Resource
    private UnderRankService underRankService;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public PageResponseResult mediaWorkList(MediaWorkDTO mediaWorkDTO, String operator) {
        Integer page = mediaWorkDTO.getPage();
        if (page == null || mediaWorkDTO.getPageSize() == null) {
            throw new RuntimeException("非法入参");
        }
        //构建limit start
        if (page > 0) {
            mediaWorkDTO.setStartNum((page - 1) * mediaWorkDTO.getPageSize());
        } else {
            mediaWorkDTO.setStartNum(0);
        }
        if (StringUtils.isNotBlank(mediaWorkDTO.getSearchInput())) {
            mediaWorkDTO.setSearchInput("%" + mediaWorkDTO.getSearchInput() + "%");
        }
        if (mediaWorkDTO.isBelongTo()) {
            mediaWorkDTO.setUploadUser(operator);
        }

        List<TMediaWork> mediaWorkList = mediaWorkMapper.selectByMediaWorkDTO(mediaWorkDTO);
        int total = mediaWorkMapper.selectByMediaWorkTotal(mediaWorkDTO);
        return new PageResponseResult(total, mediaWorkList);
    }

    @Override
    public void addMediaWork(TMediaWork mediaWork, String operator) {
        try {
            mediaWork.setId(UniqIdUtil.getUniqId());
            mediaWork.setUploadUser(operator);
            mediaWork.setStatus(EntityStatus.InValid.getCode());
            setBaseInfoForWork(mediaWork);
            resetMediaWorkInfo(mediaWork, operator);
            mediaWorkMapper.insertSelective(mediaWork);
        } catch (Exception e) {
            logger.error("文件上传记录保存失败，原因：", e);
            throw new BussException("上传失败");
        }
    }

    @Override
    public void approvalMediaWork(TMediaWork mediaWork, String operator) {
        try {
            byte status = mediaWork.getStatus();
            String approvalRemark = mediaWork.getApprovalRemark();
            mediaWork = mediaWorkMapper.selectByPrimaryKey(mediaWork.getId());
            mediaWork.setStatus(status);
            mediaWork.setApprovalUser(operator);
            mediaWork.setApprovalRemark(approvalRemark);
            resetMediaWorkInfo(mediaWork, operator);

            mediaWorkMapper.updateByPrimaryKeySelective(mediaWork);
        } catch (Exception e) {
            logger.error("文件上传记录保存失败，原因：", e);
            throw new BussException("上传失败");
        }
    }

    @Override
    public void changeMediaWorkStatus(TMediaWork mediaWork, String operator) {
        byte status = mediaWork.getStatus();
        mediaWork = this.selectMediaWorkById(mediaWork.getId());
        if (mediaWork == null) {
            throw new BussException("作品已不存在，修改失败!");
        }
        if(EntityStatus.Valid.getCode() == status){
            mediaWork.setStatus(EntityStatus.InValid.getCode());
        }else{
            mediaWork.setStatus(EntityStatus.Valid.getCode());
        }

        resetMediaWorkInfo(mediaWork, operator);
        mediaWorkMapper.updateByPrimaryKeySelective(mediaWork);
    }

    @Override
    public TMediaWork selectMediaWorkById(String id) {
        TMediaWork mediaWork = new TMediaWork();
        mediaWork = mediaWorkMapper.selectByPrimaryKey(id);
        return mediaWork;
    }

    @Override
    public void updateMediaWork(TMediaWork mediaWork) {
        mediaWorkMapper.updateByPrimaryKeySelective(mediaWork);
    }

    @Override
    public void addMediaPopular(TMediaWork mediaWork) {
        mediaWork = selectMediaWorkById(mediaWork.getId());
        if (mediaWork.getPopularNum() == null) {
            mediaWork.setPopularNum(0);
        }
        mediaWork.setPopularNum(mediaWork.getPopularNum() + 1);
        updateMediaWork(mediaWork);
    }

    //加锁防止数据重复执行
    @Override
    public synchronized void executeSortMediaWork(String params) {
        try {
            if (!redisUtil.hasKey("executeSortMediaWork")) {
                //获取一周类的所有作品列表，并获取前10
                JSONObject jsonObject = JSONUtil.parseObj(params);
                Integer intervalDay = (Integer) jsonObject.get("intervalDay");
                Integer sortNum = (Integer) jsonObject.get("sortNum");
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DATE, -intervalDay);

                TMediaWorkExample example = new TMediaWorkExample();
                example.setOrderByClause("recommend_num desc");
                example.createCriteria().andCreateDatetimeGreaterThan(calendar.getTime());
                List<TMediaWork> mediaWorkList = new ArrayList<>();
                mediaWorkList = mediaWorkMapper.selectByExample(example);
                if (mediaWorkList.size()>sortNum){
                    mediaWorkList = mediaWorkList.subList(0,10);
                }
                underRankService.addUnderMedia(mediaWorkList,"自动创建");
            }
        } catch (Exception e) {
            logger.error("对作品排序调度任务执行失败，原因：{}", e);
        } finally {
            if (redisUtil.hasKey("executeSortMediaWork")) {
                redisUtil.del("executeSortMediaWork");
            }
        }
    }

    private void resetMediaWorkInfo(TMediaWork mediaWork, String operator) {
        if (StringUtils.isBlank(mediaWork.getCreateBy())) {
            mediaWork.setCreateBy(operator);
            mediaWork.setCreateDatetime(new Date());
        }
        mediaWork.setUpdateBy(operator);
        mediaWork.setUpdateDatetime(new Date());
    }

    private void setBaseInfoForWork(TMediaWork mediaWork) {
        mediaWork.setMediaGrade(Double.valueOf("0"));
        mediaWork.setDiscussNum(0);
        mediaWork.setPopularNum(0);
        mediaWork.setRecommendNum(0);
    }
}
