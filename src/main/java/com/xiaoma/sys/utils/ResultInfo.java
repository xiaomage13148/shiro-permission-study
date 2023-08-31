package com.xiaoma.sys.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * 响应结果信息
 * @author mjh
 */
@Data
public class ResultInfo<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 0 代表请求成功
     */
    private String code;

    /**
     * 请求返回的信息
     */
    private String msg;

    /**
     * 请求返回的数据
     */
    private T data;

    public ResultInfo() {}

    public ResultInfo(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultInfo(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultInfo<T> ok() {
        this.code = CodeEnum.SUCCESS.getCode();
        this.msg = CodeEnum.SUCCESS.getDescription();
        return this;
    }

    public ResultInfo<T> ok(T data) {
        this.code = CodeEnum.SUCCESS.getCode();
        this.msg = CodeEnum.SUCCESS.getDescription();
        this.data = data;
        return this;
    }

    public ResultInfo<T> error() {
        this.code = CodeEnum.SYS_ERROR_D0100.getCode();
        this.msg = CodeEnum.SYS_ERROR_D0100.getDescription();
        return this;
    }

    public ResultInfo<T> error(T data) {
        this.code = CodeEnum.SYS_ERROR_D0100.getCode();
        this.msg = CodeEnum.SYS_ERROR_D0100.getDescription();
        this.data = data;
        return this;
    }
}
