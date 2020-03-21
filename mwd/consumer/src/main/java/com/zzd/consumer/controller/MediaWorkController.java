package com.zzd.consumer.controller;

import com.zzd.api.domain.TMediaWork;
import com.zzd.api.dto.MediaWorkDTO;
import com.zzd.api.dto.PageResponseResult;
import com.zzd.api.dto.ResponseResult;
import com.zzd.api.eunms.ErrCode;
import com.zzd.api.service.MediaWorkService;
import com.zzd.api.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author
 * @date
 * @describe
 */
@RestController
@RequestMapping("mediaWork")
public class MediaWorkController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(MediaWorkController.class);

    @Resource
    private MediaWorkService mediaWorkService;

    @RequestMapping(value = "mediaWorkList",method = RequestMethod.POST)
    @ResponseBody
    public Object mediaWorkList(@RequestBody MediaWorkDTO mediaWorkDTO) {
        try{
            logger.info("分页查询多媒体作品列表");
            PageResponseResult result = new PageResponseResult();
            result = mediaWorkService.mediaWorkList(mediaWorkDTO,getOperator());
            return ResponseResult.build(result);
        }catch (Exception e){
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "uploadMediaWork",method = RequestMethod.POST)
    @ResponseBody
    public Object uploadMediaWork( @RequestParam(value = "coverFile", required = false) MultipartFile coverFile,
                                   @RequestParam(value = "mediaFile", required = false) MultipartFile mediaFile,
                                   @RequestParam("mediaName") String mediaName,
                                   @RequestParam("mediaRemark") String mediaRemark,
                                   @RequestParam("mediaType") String mediaType) {
        try{
            if (coverFile == null || mediaFile==null) {
                return ResponseResult.error("上传文件为空，上传失败");
            }
            String coverUrl = FileUtil.uploadFile("cover",coverFile.getInputStream(),coverFile.getOriginalFilename());
            String mediaUrl = FileUtil.uploadFile("media",mediaFile.getInputStream(),mediaFile.getOriginalFilename());
            TMediaWork mediaWork = new TMediaWork();
            mediaWork.setMediaCover(coverUrl);
            mediaWork.setMediaUrl(mediaUrl);
            mediaWork.setMediaName(mediaName);
            mediaWork.setMediaRemark(mediaRemark);
            mediaWork.setMediaType(mediaType);
            mediaWorkService.addMediaWork(mediaWork,getOperator());
            return ResponseResult.ok();
        }catch (Exception e){
            return ResponseResult.error(e.getMessage());
        }
    }

}