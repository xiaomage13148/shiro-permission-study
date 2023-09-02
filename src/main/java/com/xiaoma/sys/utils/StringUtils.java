package com.xiaoma.sys.utils;

/**
 * 字符串工具类
 * @author mjh
 */
public class StringUtils {

    // 判断字符串是否为 null 或空白字符串
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    // 判断字符串是否不为 null 且不为空白字符串
    public static boolean isNotNullAndEmpty(String str) {
        return !isNullOrEmpty(str);
    }
}
