package com.zzd.consumer.controller;

import com.zzd.api.domain.TRecommend;
import com.zzd.api.dto.ResponseResult;
import com.zzd.api.service.RecommendService;
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
@RequestMapping("recommend")
public class RecommendController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(RecommendController.class);

    @Resource
    private RecommendService recommendService;

    @RequestMapping(value = "removeRecommend",method = RequestMethod.POST)
    @ResponseBody
    public Object removeRecommend(@RequestBody TRecommend recommend, HttpServletRequest request) {
        try{
            logger.info("开始删除用户推荐作品，作品ID：{}",recommend.getMediaId());
            String operator = getOperator(request);
            recommend.setUserAccount(operator);
            recommendService.removeRecommed(recommend);
            return ResponseResult.ok();
        }catch (Exception e){
            logger.error("删除用户推荐作品失败，原因：",e);
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "addRecommend",method = RequestMethod.POST)
    @ResponseBody
    public Object addRecommend(@RequestBody TRecommend recommend, HttpServletRequest request) {
        try{
            logger.info("开始添加用户推荐作品，作品ID：{}",recommend.getMediaId());
            String operator = getOperator(request);
            recommend.setUserAccount(operator);
            recommendService.addRecommend(recommend);
            return ResponseResult.ok();
        }catch (Exception e){
            logger.error("添加用户推荐作品失败，原因：",e);
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "isInRecommend",method = RequestMethod.POST)
    @ResponseBody
    public Object isInRecommend(@RequestBody TRecommend recommend, HttpServletRequest request) {
        try{
            logger.info("判断用户是否已推荐该作品");
            String operator = getOperator(request);
            recommend.setUserAccount(operator);
            boolean result = recommendService.isInRecommend(recommend);
            return ResponseResult.build(result);
        }catch (Exception e){
            logger.error("判断用户是否已推荐该作品失败，原因：",e);
            return ResponseResult.error(e.getMessage());
        }
    }

}
