package com.zzd.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zzd.api.dao.TUserMapper;
import com.zzd.api.domain.TUser;
import com.zzd.api.domain.TUserExample;
import com.zzd.api.dto.UserDTO;
import com.zzd.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 * @date
 * @describe
 */
@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private TUserMapper userMapper;

    /**
     * 根据用户账号获取用户信息
     * @param userAccout
     * @return
     */
    @Override
    public TUser selectUserByAccount(String userAccout) {
        try{
            TUserExample example = new TUserExample();
            example.createCriteria().andUserAccountEqualTo(userAccout);
            List<TUser> userList = userMapper.selectByExample(example);
            if (userList!=null && userList.size()>0){
                return userList.get(0);
            }else {
                return null;
            }
        }catch (Exception e){
            logger.error("根据用户账号获取用户信息异常，原因：",e);
            return null;
        }
    }

    @Override
    public void addUser(UserDTO userDTO) {
        try{
            TUser user = new TUser();
        }catch (Exception e){
            logger.error("添加用户信息异常，原因：",e);
        }
    }
}
