package com.zzd.api.dto;

import com.zzd.api.eunms.ErrCode;
import com.zzd.api.exceptions.BussException;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * @author
 * @date
 * @describe
 */
public class ResponseResult<T> {
    private String code;

    private T data;

    private String message;

    public ResponseResult(){}

    public ResponseResult(String code, String message){
        this.code = code;
        this.message = message;
    }


    public static <T> ResponseResult<T> build(T data) {
        ResponseResult<T> result = new ResponseResult<>(ErrCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static <T> ResponseResult<T> ok() {
        ResponseResult<T> result = new ResponseResult<>(ErrCode.SUCCESS);
        return result;
    }

    public static <T> ResponseResult<T> build(String code, String msg,T data) {
        ResponseResult<T> result = new ResponseResult<>(code, msg);
        result.setData(data);
        return result;
    }

    public static <T> ResponseResult<T> build(String code, String msg, Object... args) {
        msg = buildMsg(msg, args);
        return new ResponseResult<>(code, msg);
    }

    private ResponseResult(ErrCode error) {
        this.code = error.getCode();
        this.message = error.getDesc();
    }

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(ErrCode.SUCCESS.getCode(), ErrCode.SUCCESS.getDesc());
    }

    public static <T> ResponseResult<T> error(String msg) {
        return new ResponseResult<>(ErrCode.SYS_ERROR.getCode(), msg);
    }

    public static <T> ResponseResult<T> error(String code,String msg) {
        return new ResponseResult<>(code, msg);
    }
    public static <T> ResponseResult<T> error(ErrCode error) {
        return new ResponseResult<>(error);
    }

    public static <T> ResponseResult<T> error(String msg, Object... args) {
        msg = buildMsg(msg, args);
        return new ResponseResult<>(ErrCode.SYS_ERROR.getCode(), msg);
    }

    protected static String buildMsg(String message, Object[] args) {
        if (args != null && args.length > 0) {
            FormattingTuple ft = MessageFormatter.arrayFormat(message, args);
            message = ft.getMessage();
        }
        return message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static <T> ResponseResult<T> error(BussException e) {
        return new ResponseResult<>(e.getCode(), e.getMessage());
    }
}
