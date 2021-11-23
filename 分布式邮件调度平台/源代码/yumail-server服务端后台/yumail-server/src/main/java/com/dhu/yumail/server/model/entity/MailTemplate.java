package com.dhu.yumail.server.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 邮件模板
 * @author Yupi Li
 * @date 19/04/16
 */
@Data
public class MailTemplate implements Serializable {

    private static final long serialVersionUID = -4837886487208199809L;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 模板md代码
     */
    private String mdValue;

    /**
     * 模板内容
     */
    private String content;

    /**
     * 模板名
     */
    private String name;

    /**
     * 调度源（appId）
     */
    private String appId;

}
