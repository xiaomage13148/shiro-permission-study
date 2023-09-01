package com.xiaoma.sys.utils;

import java.util.Objects;

/**
 * 获取异常对应的信息
 */
public class ExceptionMsg {

    /**
     * 根据code获取异常对应的msg
     */
    public static String getMsgByCode(String code) {
        for (CodeEnum codeEnum : CodeEnum.values()) {
            if (Objects.equals(codeEnum.getCode(), code)) {
                return codeEnum.getDescription();
            }
        }
        return "未知异常,没有对应的状态码";
    }

}
