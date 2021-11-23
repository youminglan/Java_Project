package com.yupi.yumail.client.controller;

import com.yupi.yumail.client.facade.YumailClient;
import com.yupi.yumail.client.model.base.Result;
import com.yupi.yumail.client.model.entity.Mail;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 邮件控制器
 *
 * @author Yupi Li
 * @date 19/03/15
 */
@RequestMapping("/mails")
@RestController
public class MailController {

    @Resource
    private YumailClient yumailClient;

    @PostMapping
    public Result sendEmail(@RequestBody Mail mail) {
        mail.validate();
        yumailClient.sendMail(mail.getToUser(), mail.getSubject(), mail.getContent());
        return Result.success();
    }
}
