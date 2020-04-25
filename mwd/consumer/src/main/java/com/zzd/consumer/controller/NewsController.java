package com.zzd.consumer.controller;

import com.zzd.api.domain.TMediaWork;
import com.zzd.api.domain.TNews;
import com.zzd.api.dto.NewsDTO;
import com.zzd.api.dto.PageResponseResult;
import com.zzd.api.dto.ResponseResult;
import com.zzd.api.eunms.ErrCode;
import com.zzd.api.exceptions.BussException;
import com.zzd.api.service.NewsService;
import com.zzd.api.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            pageResponseResult = newsService.queryNewsList(newsDTO);
            return ResponseResult.build(pageResponseResult);
        }catch (Exception e){
            logger.error("查询新闻公告列表失败，原因：",e);
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "deleteNewsById",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteNewsById(@RequestBody TNews news, HttpServletRequest request){
        try{
            if (StringUtils.isBlank(news.getId())){
                throw new BussException("非法入参");
            }
            newsService.deleteNewsById(news.getId(),getOperator(request));
            return ResponseResult.ok();
        }catch (Exception e){
            logger.error("查询新闻公告列表失败，原因：",e);
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "addNews",method = RequestMethod.POST)
    @ResponseBody
    public Object addNews(@RequestParam(value = "newsFile", required = false) MultipartFile newsFile,
                          @RequestParam("title") String title,
                          @RequestParam("content") String content,
                          HttpServletRequest request){
        try{
            TNews news = new TNews();
            String newsUrl = "";
            if (newsFile!=null) {
                newsUrl =  FileUtil.uploadFile("news",newsFile.getInputStream(),newsFile.getOriginalFilename());
            }
            news.setDuration(newsUrl);
            news.setTitle(title);
            news.setContent(content);
            newsService.addNews(news,getOperator(request));
            return ResponseResult.ok();
        }catch (Exception e){
            logger.error("查询新闻公告列表失败，原因：",e);
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "downloadFile",method = RequestMethod.POST)
    public Object downloadFile(@RequestBody TNews news, HttpServletResponse response){
        try{
            if(StringUtils.isEmpty(news.getDuration())){
                logger.error("下载文件失败，文件url为空");
                return ResponseResult.error("未找到对应附件");
            }
            String fileUrl = news.getDuration();
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/")+1,fileUrl.length());
            downloadFile(news.getDuration(),fileName,response);
            return ResponseResult.ok();
        }catch (Exception e){
            logger.error("附件下载失败，原因:",e);
            return ResponseResult.error("下载失败");
        }
    }


}
