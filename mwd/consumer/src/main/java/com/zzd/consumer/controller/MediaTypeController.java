package com.zzd.consumer.controller;

import com.zzd.api.domain.TMediaType;
import com.zzd.api.dto.MediaTypeDTO;
import com.zzd.api.dto.PageResponseResult;
import com.zzd.api.dto.ResponseResult;
import com.zzd.api.service.MediaTypeService;
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
@RequestMapping("/mediaType")
public class MediaTypeController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(MediaTypeController.class);

    @Resource
    private MediaTypeService mediaTypeService;


    @RequestMapping(value = "queryMediaTypeList",method = RequestMethod.POST)
    @ResponseBody
    public Object queryMediaTypeList(@RequestBody MediaTypeDTO mediaTypeDTO) {
        try{
            PageResponseResult result = new PageResponseResult();
            result = mediaTypeService.queryMediaTypeListByDTO(mediaTypeDTO);
            return ResponseResult.build(result);
        }catch (Exception e){
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "getAllMediaType",method = RequestMethod.POST)
    @ResponseBody
    public Object getAllMediaType(@RequestBody TMediaType mediaType) {
        try{
            List<TMediaType> mediaTypeList = new ArrayList<>();
            mediaTypeList = mediaTypeService.getAllMediaType(mediaType.getStatus());
            return ResponseResult.build(mediaTypeList);
        }catch (Exception e){
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "addMediaType",method = RequestMethod.POST)
    @ResponseBody
    public Object addMediaType(@RequestBody TMediaType mediaType, HttpServletRequest request) {
        try{
            String operator = getOperator(request);
            mediaTypeService.addMediaType(mediaType,operator);
            return ResponseResult.ok();
        }catch (Exception e){
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "changeMediaTypeStatus",method = RequestMethod.POST)
    @ResponseBody
    public Object changeMediaTypeStatus(@RequestBody TMediaType mediaType,HttpServletRequest request) {
        try{
            String operator = getOperator(request);
            mediaTypeService.changeMediaTypeStatus(mediaType,operator);
            return ResponseResult.ok();
        }catch (Exception e){
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "queryMediaTypeById",method = RequestMethod.POST)
    @ResponseBody
    public Object queryMediaTypeById(@RequestBody TMediaType mediaType,HttpServletRequest request) {
        try{
            String operator = getOperator(request);
            mediaType = mediaTypeService.queryMediaTypeById(mediaType.getId());
            return ResponseResult.build(mediaType);
        }catch (Exception e){
            return ResponseResult.error(e.getMessage());
        }
    }

}
