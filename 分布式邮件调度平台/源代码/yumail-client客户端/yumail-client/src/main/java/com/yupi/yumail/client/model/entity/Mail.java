package com.yupi.yumail.client.model.entity;

import com.yupi.yumail.client.utils.Assert;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 邮件包装类
 * @author Yupi Li
 * @date 19/03/15
 */
@Data
public class Mail implements Serializable {

    /**
     * 接收者邮箱地址
     */
    private String toUser;
    /**
     * 主题
     */
    private String subject;
    /**
     * 内容
     */
    private String content;

    /**
     * 是否定时
     */
    private Boolean timer;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * crontab表达式
     */
    private String crontab;

    /**
     * 调度源
     */
    private String source;

    private static final long serialVersionUID = -3115272012566756830L;

    public void validate() {
        Assert.notNull(toUser, subject, timer, source);
        if (timer) {
            Assert.notNull(crontab);
        }
    }

}
