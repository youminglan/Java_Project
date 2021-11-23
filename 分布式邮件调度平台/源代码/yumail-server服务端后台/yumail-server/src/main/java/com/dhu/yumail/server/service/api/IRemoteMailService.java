package com.dhu.yumail.server.service.api;

import com.dhu.yumail.server.model.entity.Mail;

/**
 * 邮件服务接口
 * @author Yupi Li
 * @date 19/03/15
 */
public interface IRemoteMailService {

    /**
     * 发送邮件（定时/非定时，本地/非本地）
     * @param mail
     */
    void sendMail(Mail mail);

}
