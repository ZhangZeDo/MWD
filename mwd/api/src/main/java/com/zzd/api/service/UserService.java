package com.zzd.api.service;

import com.zzd.api.domain.TUser;
import com.zzd.api.dto.UserDTO;

import java.util.List;

/**
 * @author
 * @date
 * @describe
 */
public interface UserService {
    //根据用户账号获取用户信息
    TUser selectUserByAccount(String userAccout,byte userStatus);

    //新增用户
    void addUser(UserDTO userDTO,String operator);

    //删除用户
    void removeUser(List<String> userAccountList, String operator);

    //更新用户信息
    void updateUser(UserDTO userDTO,String operator);


}
