package com.zzd.api.exceptions;

import com.zzd.api.eunms.ErrCode;

/**
 * @author
 * @date
 * @describe
 */
public class BussException  extends RuntimeException{
    private static final long serialVersionUID = -4202075665101686515L;
    /**
     * 错误码
     */
    private String code;
    /**
     * 错误描述
     */
    private String message;

    public BussException(String message) {
        this.code = ErrCode.SYS_ERROR.getCode();
        this.message = message;
    }

    public BussException(String message, Throwable cause) {
        this(message);
        addSuppressed(cause);
    }

    public BussException(Throwable cause) {
        this(ErrCode.SYS_ERROR.getDesc());
        addSuppressed(cause);
    }

    public BussException(ErrCode errCode){
        this.code = errCode.getCode();
        this.message = errCode.getDesc();
    }

    public BussException(ErrCode errCode, Throwable cause){
        this(errCode);
        addSuppressed(cause);
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
