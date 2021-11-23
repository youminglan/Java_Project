package com.yupi.yumail.client.utils;

import java.util.Date;
import java.util.Optional;

/**
 * 日志模板
 * @author Yupi Li
 * @date 19/03/19
 */
public class LogTemplate {

    public static String sendMailTemplate(String source, String fromUser, String toUser, String content) {
        return String.format("<div>" +
                        "<p>调度源 %s (%s)</p>" +
                        "<p>发送至：%s</p>" +
                        "<p>内容：</p>" +
                        "<div>%s</div>" +
                        "</div>",
                source, fromUser, toUser, content);
    }

    public static String startJobTemplate(String jobName, String jobGroupName, Class jobClass, String cron, Date startTime, Date endTime) {
        startTime = Optional.ofNullable(startTime).orElse(new Date());
        String endTimeStr = endTime == null ? "持续" : DateFormatter.getISODateStr(endTime);
        return String.format("<div>" +
                        "<p>工作名：%s</p>" +
                        "<p>工作组：%s</p>" +
                        "<p style=\"color: green\">启动！</p>" +
                        "<p>工作类型：%s</p>" +
                        "<p>定时任务表达式：%s</p>" +
                        "<p>调度时间：%s —— %s</p>" +
                        "<div>",
                jobName, jobGroupName, jobClass.getName(), cron, DateFormatter.getISODateStr(startTime), endTimeStr);
    }

    public static String endJobTemplate(String jobName, String jobGroupName, Class jobClass) {
        return String.format("<div>" +
                        "<p>工作名：%s</p>" +
                        "<p>工作组：%s</p>" +
                        "<p>终止！</p>" +
                        "<p>工作类型：%s</p>" +
                        "</div>",
                jobName, jobGroupName, jobClass.getName());
    }


}
