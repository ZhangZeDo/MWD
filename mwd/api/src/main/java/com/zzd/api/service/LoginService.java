package com.zzd.api.service;

import com.zzd.api.domain.TUser;

/**
 * @author
 * @date
 * @describe
 */
public interface LoginService {
    void createLoginInfo(TUser user);

    TUser getLoginInfo();

    void loginOut();
}
