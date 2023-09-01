package com.xiaoma.sys.validation;

import java.lang.reflect.Field;

/**
 * 对象校验
 * @author mjh
 */
public class ObjectValidator {
    /**
     * 校验对象中的属性是否都存在值
     */
    public static boolean validateObjectFields(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        
        for (Field field : fields) {
            field.setAccessible(true); // 允许访问私有字段
            Object value = field.get(obj);
            
            if (value == null) {
                return false; // 如果任何属性为 null，则返回 false
            }
            
            if (value instanceof String && ((String) value).isEmpty()) {
                return false; // 如果字符串属性为空，则返回 false
            }
        }
        
        return true; // 所有属性都有值，返回 true
    }
}
