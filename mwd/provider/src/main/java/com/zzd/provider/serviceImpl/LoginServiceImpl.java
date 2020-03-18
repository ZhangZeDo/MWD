package com.zzd.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zzd.api.domain.TUser;
import com.zzd.api.exceptions.BussException;
import com.zzd.api.service.LoginService;
import com.zzd.provider.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author
 * @date
 * @describe
 */
@Service
public class LoginServiceImpl implements LoginService {
    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void createLoginInfo(TUser user) {
        try {
            redisUtil.set("LoginInfo",user);
        }catch (Exception e){
            throw new BussException("登录异常，请重新登录");
        }
    }

    @Override
    public TUser getLoginInfo() {
        try {
            if (!redisUtil.hasKey("LoginInfo")){
                throw new BussException("登录异常，请重新登录");
            }
            TUser user = (TUser)redisUtil.get("LoginInfo");
            return user;
        }catch (Exception e){
            throw new BussException("登录异常，请重新登录");
        }
    }

    @Override
    public void loginOut() {
        try {
            if (redisUtil.hasKey("LoginInfo")){
                redisUtil.del("LoginInfo");
            }
        }catch (Exception e){
            throw new BussException("登录异常，请重新登录");
        }
    }
}
