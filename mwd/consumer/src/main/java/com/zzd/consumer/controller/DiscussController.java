package com.zzd.consumer.controller;

import com.zzd.api.domain.TDiscuss;
import com.zzd.api.dto.BookmarkDTO;
import com.zzd.api.dto.DiscussDTO;
import com.zzd.api.dto.PageResponseResult;
import com.zzd.api.dto.ResponseResult;
import com.zzd.api.service.DiscussService;
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
@RequestMapping("/discuss")
public class DiscussController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(DiscussController.class);

    @Resource
    private DiscussService discussService;

    @RequestMapping(value = "selectDiscussList",method = RequestMethod.POST)
    @ResponseBody
    public Object selectDiscussList(@RequestBody DiscussDTO discussDTO, HttpServletRequest request) {
        try{
            logger.info("开始分页获取作品评论列表");
            PageResponseResult result = new PageResponseResult();
            result = discussService.selectDiscussList(discussDTO);
            return ResponseResult.build(result);
        }catch (Exception e){
            logger.error("获取作品评论列表失败，原因：",e);
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "sendDiscuss",method = RequestMethod.POST)
    @ResponseBody
    public Object sendDiscuss(@RequestBody TDiscuss discuss, HttpServletRequest request) {
        try{
            logger.info("开始在作品下创建评论");
            String operator = getOperator(request);
            discussService.sendDiscuss(discuss,operator);
            return ResponseResult.ok();
        }catch (Exception e){
            logger.error("在作品下创建评论失败，原因：",e);
            return ResponseResult.error(e.getMessage());
        }
    }
}
