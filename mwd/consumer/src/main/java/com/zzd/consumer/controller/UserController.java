package com.zzd.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zzd.api.domain.TUser;
import com.zzd.api.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author
 * @date
 * @describe
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "selectUserByAccount")
    @ResponseBody
    public Object selectUserByAccount() {
        try{
            TUser user = userService.selectUserByAccount("123456");
            return user;
        }catch (Exception e){
            return null;
        }
    }
}
