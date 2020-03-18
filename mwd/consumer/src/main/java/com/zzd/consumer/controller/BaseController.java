package com.zzd.consumer.controller;

import com.zzd.api.domain.TUser;
import com.zzd.api.service.LoginService;
import com.zzd.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author
 * @date
 * @describe
 */
public class BaseController {
    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Resource
    private LoginService loginService;

    public String getOperator(){
        TUser user = loginService.getLoginInfo();
        return user.getUserAccount();
    }
}
