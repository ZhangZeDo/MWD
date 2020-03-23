package com.zzd.consumer.config;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONObject;
import com.zzd.api.domain.TUser;
import com.zzd.api.eunms.ErrCode;
import com.zzd.api.exceptions.BussException;
import com.zzd.api.service.ErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author
 * @date
 * @describe
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        TUser user = (TUser) session.getAttribute("userInfo");
        response.setContentType("text/html;charset=UTF-8");
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers","Origin,Content-Type,Accept,token,X-Requested-With");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        if (user == null){
            String jsonString = JSON.json(new BussException(ErrCode.LOGIN_ERROR));
            response.getWriter().write(jsonString);
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
