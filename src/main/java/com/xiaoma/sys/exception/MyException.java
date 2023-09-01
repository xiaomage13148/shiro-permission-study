package com.xiaoma.sys.exception;

import com.xiaoma.sys.utils.ExceptionMsg;

/**
 * 自定义异常
 */
public class MyException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    /**
     * 异常编码
     */
    private String code;

    /**
     * 异常信息
     */
    private String msg;

    public MyException(String code) {
        this.code = code;
        this.msg = ExceptionMsg.getMsgByCode(code);
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
