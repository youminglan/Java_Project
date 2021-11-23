package com.yupi.yumail.client.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志对象
 * @author Yupi Li
 * @date 19/03/16
 */
@Data
public class Log implements Serializable {

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 日志级别
     */
    private Integer level;

    /**
     * 调度源（appId）
     */
    private String appId;

    /**
     * 内容
     */
    private String content;

    public Log(String appId, String content, Integer level) {
        this.createTime = new Date();
        this.level = level;
        this.appId = appId;
        this.content = content;
    }

    private static final long serialVersionUID = 4666756922383637115L;
}
