package com.zzd.api.service;

import com.zzd.api.domain.TUser;
import com.zzd.api.dto.UserDTO;

/**
 * @author
 * @date
 * @describe
 */
public interface UserService {
    /**
     * 根据用户账号获取用户信息
     * @param userAccout
     * @return
     */
    TUser selectUserByAccount(String userAccout);

    void addUser(UserDTO userDTO);
}
