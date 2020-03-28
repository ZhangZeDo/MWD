package com.zzd.consumer.controller;

import com.zzd.api.domain.TMediaWork;
import com.zzd.api.domain.TUnderRank;
import com.zzd.api.dto.ResponseResult;
import com.zzd.api.service.UnderRankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date
 * @describe
 */
@RestController
@RequestMapping("/underRank")
public class UnderRankController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UnderRankService underRankService;

    @RequestMapping(value = "queryAllUnderMedia",method = RequestMethod.POST)
    @ResponseBody
    public Object queryAllUnderMedia(@RequestBody TUnderRank underRank) {
        try{
            List<TMediaWork> mediaWorkList = new ArrayList<>();
            mediaWorkList = underRankService.queryAllUnderMedia(underRank.getStatus());
            return ResponseResult.build(mediaWorkList);
        }catch (Exception e){
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "queryAllUnderRank",method = RequestMethod.POST)
    @ResponseBody
    public Object queryAllUnderRank(@RequestBody TUnderRank underRank) {
        try{
            List<TUnderRank> underRankList = new ArrayList<>();
            underRankList = underRankService.queryAllUnderRank(underRank.getStatus());
            return ResponseResult.build(underRankList);
        }catch (Exception e){
            return ResponseResult.error(e.getMessage());
        }
    }
}
