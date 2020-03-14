package com.zzd.api.eunms;

/**
 * @author
 * @date
 * @describe
 */
public enum UserRoleType {
    Ordinary("ordinary","普通用户"),
    Admin("admin","管理员"),
    SuperAdmin("superAdmin","超级管理员");

    private String type;
    private String message;

    UserRoleType(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
