package com.zzd.consumer.controller;

import com.zzd.api.dto.NewsDTO;
import com.zzd.api.dto.PageResponseResult;
import com.zzd.api.dto.ResponseResult;
import com.zzd.api.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * @date
 * @describe
 */
@Controller
@RequestMapping("/news")
public class NewsController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Resource
    private NewsService newsService;

    @RequestMapping(value = "queryNewsList",method = RequestMethod.POST)
    @ResponseBody
    public Object queryNewsList(@RequestBody NewsDTO newsDTO){
        try{
            PageResponseResult pageResponseResult = new PageResponseResult();
            return ResponseResult.build(pageResponseResult);
        }catch (Exception e){
            logger.error("查询新闻公告列表失败，原因：",e);
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "deleteNewsById",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteNewsById(@RequestBody NewsDTO newsDTO, HttpServletRequest request){
        try{
            PageResponseResult pageResponseResult = new PageResponseResult();
            return ResponseResult.build(pageResponseResult);
        }catch (Exception e){
            logger.error("查询新闻公告列表失败，原因：",e);
            return ResponseResult.error(e.getMessage());
        }
    }
}
