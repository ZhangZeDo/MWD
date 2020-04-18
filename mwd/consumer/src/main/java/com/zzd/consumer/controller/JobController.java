package com.zzd.consumer.controller;

import com.zzd.api.domain.TJob;
import com.zzd.api.dto.JobDTO;
import com.zzd.api.dto.MediaTypeDTO;
import com.zzd.api.dto.PageResponseResult;
import com.zzd.api.dto.ResponseResult;
import com.zzd.api.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * @date
 * @describe
 */
@RestController
@RequestMapping("/job")
public class JobController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(JobController.class);

    @Resource
    private JobService jobService;

    @RequestMapping(value = "queryJobList",method = RequestMethod.POST)
    @ResponseBody
    public Object queryJobList(@RequestBody JobDTO jobDTO) {
        try{
            logger.info("开始分页查询任务列表");
            PageResponseResult result = new PageResponseResult();
            result = jobService.queryJobList(jobDTO);
            return ResponseResult.build(result);
        }catch (Exception e){
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "addJob",method = RequestMethod.POST)
    @ResponseBody
    public Object addJob(@RequestBody TJob job, HttpServletRequest request) {
        try{
            logger.info("用户新增定时任务");
            jobService.insert(job,getOperator(request));
            return ResponseResult.ok();
        }catch (Exception e){
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "editJob",method = RequestMethod.POST)
    @ResponseBody
    public Object editJob(@RequestBody TJob job,HttpServletRequest request) {
        try{
            logger.info("用户编辑定时任务");
            jobService.update(job,getOperator(request));
            return ResponseResult.ok();
        }catch (Exception e){
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "runJob",method = RequestMethod.POST)
    @ResponseBody
    public Object runJob(@RequestBody TJob job) {
        try{
            logger.info("用户立即执行定时任务");
            jobService.runOnce(job);
            return ResponseResult.ok();
        }catch (Exception e){
            return ResponseResult.error(e.getMessage());
        }
    }


}
