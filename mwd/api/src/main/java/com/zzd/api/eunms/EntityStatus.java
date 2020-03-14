package com.zzd.api.eunms;

/**
 * @author
 * @date
 * @describe
 */
public enum EntityStatus {
    Valid((byte)1,"有效"),
    InValid((byte)0,"无效"),
    All((byte)2,"所有");


    private byte code;
    private String msg;

    EntityStatus(byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
