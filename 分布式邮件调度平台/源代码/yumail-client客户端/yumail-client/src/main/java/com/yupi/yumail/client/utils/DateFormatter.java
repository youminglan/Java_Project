package com.yupi.yumail.client.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * 日期格式化工具类
 * @author Yupi Li
 * @date 19/03/06
 */
public class DateFormatter {

    public static String getISODateStr(Date date) {
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getISODateStrOfNow() {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
    }
}
