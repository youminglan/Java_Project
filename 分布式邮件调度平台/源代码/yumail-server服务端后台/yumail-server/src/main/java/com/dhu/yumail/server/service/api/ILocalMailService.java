package com.dhu.yumail.server.service.api;

import com.dhu.yumail.server.model.entity.Mail;

/**
 * 本地邮件服务接口（不进行远程调用）
 * @author Yupi Li
 * @date 19/03/15
 */
public interface ILocalMailService {

    /**
     * 发送邮件（定时/单次）
     * @param mail
     */
    void sendMail(Mail mail);

    void sendHtmlMail(String toUser, String subject, String content);

}
