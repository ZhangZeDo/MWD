package com.zzd.consumer.controller;

import com.zzd.api.domain.TBookmark;
import com.zzd.api.domain.TMediaWork;
import com.zzd.api.dto.PageResponseResult;
import com.zzd.api.dto.ResponseResult;
import com.zzd.api.dto.BookmarkDTO;
import com.zzd.api.service.BookmarkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date
 * @describe
 */
@RestController
@RequestMapping("/bookmark")
public class BookMarkController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(BookMarkController.class);

    @Resource
    private BookmarkService bookmarkService;

    @RequestMapping(value = "getBookMediaList",method = RequestMethod.POST)
    @ResponseBody
    public Object getBookMediaList(@RequestBody BookmarkDTO bookmarkDTO, HttpServletRequest request) {
        try{
            logger.info("开始分页获取用户作品收藏列表");
            String operator = getOperator(request);
            bookmarkDTO.setUserAccount(operator);
            PageResponseResult result = new PageResponseResult();
            result = bookmarkService.listBookmarkList(bookmarkDTO);
            return ResponseResult.build(result);
        }catch (Exception e){
            logger.error("获取用户收藏列表失败，原因：",e);
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "removeBookmark",method = RequestMethod.POST)
    @ResponseBody
    public Object removeBookmark(@RequestBody TBookmark bookmark, HttpServletRequest request) {
        try{
            logger.info("开始删除用户收藏作品，作品ID：{}",bookmark.getMediaId());
            String operator = getOperator(request);
            bookmark.setUserAccount(operator);
            bookmarkService.removeBookmark(bookmark);
            return ResponseResult.ok();
        }catch (Exception e){
            logger.error("删除用户收藏作品失败，原因：",e);
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "addBookmark",method = RequestMethod.POST)
    @ResponseBody
    public Object addBookmark(@RequestBody TBookmark bookmark, HttpServletRequest request) {
        try{
            logger.info("开始添加用户收藏作品，作品ID：{}",bookmark.getMediaId());
            String operator = getOperator(request);
            bookmark.setUserAccount(operator);
            bookmarkService.addBookmark(bookmark);
            return ResponseResult.ok();
        }catch (Exception e){
            logger.error("添加用户收藏作品失败，原因：",e);
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "isInBookmark",method = RequestMethod.POST)
    @ResponseBody
    public Object isInBookmark(@RequestBody TBookmark bookmark, HttpServletRequest request) {
        try{
            logger.info("判断用户是否已收藏该作品");
            String operator = getOperator(request);
            bookmark.setUserAccount(operator);
            boolean result = bookmarkService.isBookMark(bookmark);
            return ResponseResult.build(result);
        }catch (Exception e){
            logger.error("判断用户是否已收藏该作品失败，原因：",e);
            return ResponseResult.error(e.getMessage());
        }
    }
}
