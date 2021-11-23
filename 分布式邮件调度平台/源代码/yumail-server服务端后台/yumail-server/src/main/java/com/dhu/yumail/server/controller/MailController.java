package com.dhu.yumail.server.controller;

import com.dhu.yumail.server.model.base.Result;
import com.dhu.yumail.server.model.entity.Mail;
import com.dhu.yumail.server.properties.YumailServerProperties;
import com.dhu.yumail.server.service.api.ILocalMailService;
import com.dhu.yumail.server.service.api.IRemoteMailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 邮件控制器
 * @author Yupi Li
 * @date 19/03/15
 */
@RequestMapping("/mails")
@RestController
public class MailController {

    @Resource
    private IRemoteMailService remoteMailService;

    @Resource
    private ILocalMailService localMailService;

    @Resource
    private YumailServerProperties serverProperties;

    /**
     * 给管理界面的接口
     * @param mail
     * @return
     */
    @PostMapping
    public Result sendEmail(@RequestBody Mail mail) {
        mail.validate();
        // 是本地源
        if (mail.getSource().equals(serverProperties.getAppId())) {
            localMailService.sendMail(mail);
        } else {
            remoteMailService.sendMail(mail);
        }
        return Result.success();
    }
}
