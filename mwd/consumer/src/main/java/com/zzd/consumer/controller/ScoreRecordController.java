package com.zzd.consumer.controller;

import com.zzd.api.domain.TRecommend;
import com.zzd.api.domain.TScoreRecord;
import com.zzd.api.dto.ResponseResult;
import com.zzd.api.service.ScoreRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * @date
 * @describe
 */
@RestController
@RequestMapping("scoreRecord")
public class ScoreRecordController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(ScoreRecordController.class);

    @Resource
    private ScoreRecordService scoreRecordService;

    @RequestMapping(value = "addScoreRecord",method = RequestMethod.POST)
    @ResponseBody
    public Object addScoreRecord(@RequestBody TScoreRecord scoreRecord, HttpServletRequest request) {
        try{
            logger.info("用户对作品评分，作品ID：{}",scoreRecord.getMediaId());
            scoreRecordService.addScoreRecord(scoreRecord,getOperator(request));
            return ResponseResult.ok();
        }catch (Exception e){
            logger.error("用户对作品评分失败，原因：",e);
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "isInScoreRecord",method = RequestMethod.POST)
    @ResponseBody
    public Object isInScoreRecord(@RequestBody TScoreRecord scoreRecord, HttpServletRequest request) {
        try{
            logger.info("判断用户是否已对作品评分，作品ID：{}",scoreRecord.getMediaId());
            boolean result = scoreRecordService.isInScoreRecord(scoreRecord,getOperator(request));
            return ResponseResult.build(result);
        }catch (Exception e){
            logger.error("判断用户是否已对作品评分失败，原因：",e);
            return ResponseResult.error(e.getMessage());
        }
    }

}
