package com.dhu.yumail.server.utils;

import com.dhu.yumail.server.constant.ExceptionEnum;
import com.dhu.yumail.server.model.base.BusinessException;

import java.text.SimpleDateFormat;
import java.util.Collection;

/**
 * 断言
 * @author Yupi Li
 * @date 19/01/14
 */
public class Assert {

    /**
     * 非空
     * @param objects
     */
    public static void notNull(Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                throw new BusinessException(ExceptionEnum.PARAM_NULL);
            }
            if (object instanceof String) {
                notBlank((String) object);
            }
        }
    }

    /**
     * 为空
     * @param object
     */
    public static void isNull(Object object) {
        if (object != null) {
            throw new BusinessException(ExceptionEnum.NOT_NULL);
        }
    }

    /**
     * 为空（自定义提示信息）
     * @param object
     * @param msg
     */
    public static void isNull(Object object, String msg) {
        if (object != null) {
            throw new BusinessException(ExceptionEnum.NOT_NULL, msg);
        }
    }

    /**
     * 字符非空且长度不为0
     * @param strings
     */
    public static void notBlank(String... strings) {
        for (String str : strings) {
            if (str == null || str.length() == 0) {
                throw new BusinessException(ExceptionEnum.PARAM_NULL);
            }
        }
    }

    public static void notEmpty(Object[] objects) {
        if (objects == null || objects.length == 0) {
            throw new BusinessException(ExceptionEnum.ARRAY_EMPTY);
        }
    }

    /**
     * 集合非空且元素数不为0
     * @param collection
     */
    public static void notEmpty(Collection collection) {
        if (collection == null || collection.size() == 0) {
            throw new BusinessException(ExceptionEnum.NO_RESULT);
        }
    }

    /**
     * 元素非空，抛出“未找到结果”
     * @param object
     */
    public static void hasFound(Object object) {
        if (object == null) {
            throw new BusinessException(ExceptionEnum.NO_RESULT);
        }
    }

    /**
     * 元素非空，抛出未找到结果，自定义异常信息
     * @param object
     * @param msg
     */
    public static void hasFound(Object object, String msg) {
        if (object == null) {
            throw new BusinessException(ExceptionEnum.NO_RESULT, msg);
        }
    }

    /**
     * 元素非空，抛出自定义异常枚举的信息
     * @param object
     * @param e
     */
    public static void hasFound(Object object, ExceptionEnum e) {
        hasFound(object, e.getMsg());
    }

    /**
     * 日期校验
     * @param date
     */
    public static void isDate(String date) {
        notNull(date);
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            dateFormat.parse(date);
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.DATA_FORMAT_ERROR, "日期格式错误");
        }
    }
}