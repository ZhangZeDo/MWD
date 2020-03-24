package com.zzd.consumer.controller;

import com.zzd.api.domain.TUser;
import com.zzd.api.service.LoginService;
import com.zzd.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author
 * @date
 * @describe
 */
public class BaseController {
    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    public String getOperator(HttpServletRequest request){
        HttpSession session = request.getSession();
        TUser user = (TUser) session.getAttribute("userInfo");
        return user.getUserAccount();
    }
}
