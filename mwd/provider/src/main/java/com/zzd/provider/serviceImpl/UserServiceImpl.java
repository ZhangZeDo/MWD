package com.zzd.provider.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.zzd.api.dao.TUserMapper;
import com.zzd.api.dto.PageResponseResult;
import com.zzd.api.eunms.EntityStatus;
import com.zzd.api.domain.TUser;
import com.zzd.api.domain.TUserExample;
import com.zzd.api.dto.UserDTO;
import com.zzd.api.exceptions.BussException;
import com.zzd.api.service.LoginService;
import com.zzd.api.service.UserService;
import com.zzd.provider.utils.UniqIdUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;
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
    @Resource
    private LoginService loginService;

    /**
     * 根据用户账号获取用户信息
     * @param userAccout
     * @return
     */
    @Override
    public TUser selectUserByAccount(String userAccout,byte userStatus) {
        try{
            if (StringUtils.isBlank(userAccout)){
                logger.error("根据用户账号查询用户信息失败，账号为空");
                throw new BussException("用户账号为空");
            }

            TUserExample example = new TUserExample();
            if (userStatus == EntityStatus.All.getCode()){
                example.createCriteria().andUserAccountEqualTo(userAccout);
            }else{
                example.createCriteria().andUserAccountEqualTo(userAccout).andStatusEqualTo(userStatus);
            }
            List<TUser> userList = userMapper.selectByExample(example);
            if (userList!=null && userList.size()>0){
                return userList.get(0);
            }else {
                return null;
            }
        }catch (Exception e){
            logger.error("根据用户账号获取用户信息异常，原因：",e);
            throw new BussException("根据用户账号获取用户信息失败");
        }
    }

    @Override
    public void addUser(UserDTO userDTO,String operator) {
        try{
            if (StringUtils.isBlank(userDTO.getUserAccount()) || StringUtils.isBlank(userDTO.getUserName()) || StringUtils.isBlank(userDTO.getUserPassword())){
                logger.error("非法入参");
                throw new BussException("用户信息不全，请重新输入");
            }
            TUser user = new TUser();
            user = selectUserByAccount(userDTO.getUserAccount(),EntityStatus.All.getCode());
            if (user != null){
                logger.error("用户号已存在，添加用户信息失败");
                throw new BussException("用户号已存在");
            }
            user = new TUser();
            BeanUtil.copyProperties(userDTO,user);
            user.setId(UniqIdUtil.getUniqId());
            user.setStatus(EntityStatus.Valid.getCode());
            resetUserBase(user,operator);
            userMapper.insertSelective(user);
        }catch (Exception e){
            logger.error("添加用户信息异常，原因：",e);
            throw new BussException("添加用户信息失败");
        }
    }

    @Override
    public void removeUser(List<String> userAccountList,String operator) {
        try{
            for (String userAccount : userAccountList) {
                TUser user = selectUserByAccount(userAccount,EntityStatus.All.getCode());
                if (user != null){
                    user.setStatus(EntityStatus.InValid.getCode());
                    resetUserBase(user,operator);
                    userMapper.updateByPrimaryKey(user);
                }
            }
        }catch (Exception e){
            logger.error("删除用户信息异常，原因：",e);
            throw new BussException("删除用户信息失败");
        }
    }

    @Override
    public void updateUser(UserDTO userDTO,String operator) {
        try{
            TUser user = new TUser();
            user = selectUserByAccount(userDTO.getUserAccount(),EntityStatus.Valid.getCode());
            if (user == null){
                logger.error("要修改的用户不存在，用户账号为：",userDTO.getUserAccount());
                throw new BussException("要修改的用户不存在");
            }
            BeanUtil.copyProperties(userDTO,user);
            resetUserBase(user,operator);
            userMapper.updateByPrimaryKeySelective(user);

            if (StringUtils.equals(userDTO.getUserAccount(),operator)){
                TUser newUser = selectUserByAccount(userDTO.getUserAccount(),EntityStatus.Valid.getCode());
                loginService.createLoginInfo(newUser);
            }

        }catch (Exception e){
            logger.error("更新用户信息异常，原因：",e);
            throw new BussException("更新用户信息失败");
        }
    }

    @Override
    public PageResponseResult queryUserList(UserDTO userDTO) {
        Integer page = userDTO.getPage();
        if (page == null || userDTO.getPageSize() == null) {
            throw new RuntimeException("非法入参");
        }
        //构建limit start
        if (page > 0) {
            userDTO.setStartNum((page - 1) * userDTO.getPageSize());
        } else {
            userDTO.setStartNum(0);
        }
        if(StringUtils.isNotBlank(userDTO.getSearchInput())){
            userDTO.setSearchInput("%"+userDTO.getSearchInput()+"%");
        }

        List<TUser> userList = userMapper.selectUserByDTO(userDTO);
        int total = userMapper.selectUserTotalByDTO(userDTO);
        return new PageResponseResult(total,userList);
    }

    @Override
    public void changeUserStatus(UserDTO userDTO, String operator) {
        String status = userDTO.getStatus();
        TUser user = userMapper.selectByPrimaryKey(userDTO.getId());
        if(StringUtils.equals(status,"0")){
            user.setStatus(EntityStatus.Valid.getCode());
        }else if (StringUtils.equals(status,"1")){
            user.setStatus(EntityStatus.InValid.getCode());
        }else{
            throw new BussException("用户状态异常");
        }
        resetUserBase(user,operator);
        userMapper.updateByPrimaryKeySelective(user);

    }

    @Override
    public void changeUserPassword(UserDTO userDTO, String operator) {
        String password = userDTO.getUserPassword();
        TUser user = selectUserByAccount(userDTO.getUserAccount(),EntityStatus.Valid.getCode());
        resetUserBase(user,operator);
        user.setUserPassword(password);
        userMapper.updateByPrimaryKeySelective(user);
    }

    private void resetUserBase(TUser user,String operator){
        if (StringUtils.isBlank(user.getCreateBy())){
            user.setCreateBy(operator);
            user.setCreateDatetime(new Date());
        }
        user.setUpdateBy(operator);
        user.setUpdateDatetime(new Date());
    }
}
