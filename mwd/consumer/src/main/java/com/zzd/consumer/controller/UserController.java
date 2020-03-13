package com.zzd.consumer.controller;

import com.zzd.api.domain.TUser;
import com.zzd.api.dto.ResponseResult;
import com.zzd.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author
 * @date
 * @describe
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @RequestMapping(value = "selectUserByAccount",method = RequestMethod.GET)
    @ResponseBody
    public Object selectUserByAccount() {
        try{
            TUser user = userService.selectUserByAccount("123456");
            return ResponseResult.build(user);
        }catch (Exception e){
            return ResponseResult.error(e.getMessage());
        }
    }
}
