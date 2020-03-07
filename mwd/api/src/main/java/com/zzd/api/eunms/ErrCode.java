package com.zzd.api.eunms;

import com.zzd.api.service.ErrorCode;

/**
 * @author
 * @date
 * @describe
 */
public enum ErrCode implements ErrorCode {
    ILLEGAL_ARGUMENT("403", "非法参数!"),
    SYS_ERROR("444","系统异常, 请重试"),
    BAD_USER_PASSWORD("446", "用户名或密码错误!"),
    SUCCESS("200","success"),
    ISNULL("420","error");


    private String code;

    private String desc;

    ErrCode(String code) {
        this.code = code;
    }

    ErrCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return desc;
    }
}
