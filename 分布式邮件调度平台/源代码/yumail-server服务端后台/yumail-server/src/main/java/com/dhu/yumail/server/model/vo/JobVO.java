package com.dhu.yumail.server.model.vo;

import com.dhu.yumail.server.model.entity.Mail;
import lombok.Data;
import org.quartz.Trigger;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务包装类
 * @author Yupi Li
 * @date 19/03/22
 */
@Data
public class JobVO implements Serializable {

    /**
     * 邮件信息
     */
    private Mail mail;



    /**
     * 状态
     */
    private Integer state;

    /**
     * 工作名
     */
    private String jobName;

    /**
     * 工作组名
     */
    private String jobGroupName;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 上次调度时间
     */
    private Date previousFireTime;

    /**
     * 下次调度时间
     */
    private Date nextFireTime;


    private static final long serialVersionUID = -654956488863895435L;

}
