package com.dhu.yumail.server.service.impl;

import com.dhu.yumail.server.job.LocalSendMailJob;
import com.dhu.yumail.server.model.entity.Mail;
import com.dhu.yumail.server.properties.YumailServerProperties;
import com.dhu.yumail.server.service.api.ILocalMailService;
import com.dhu.yumail.server.service.api.ILogService;
import com.dhu.yumail.server.service.api.IQuartzService;
import com.dhu.yumail.server.utils.LogTemplate;
import lombok.Builder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 本地邮件服务实现类
 *
 * @author Yupi Li
 * @date 19/03/15
 */
@Service
public class LocalMailServiceImpl implements ILocalMailService {

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private IQuartzService quartzService;

    @Resource
    private ILogService logService;

    @Resource
    private YumailServerProperties yumailServerProperties;


    @Override
    public void sendMail(Mail mail) {
        // 未定时，直接发送
        if (!mail.getTimer()) {
            sendHtmlMail(mail.getToUser(), mail.getSubject(), mail.getContent());
        } else {
            startSendMailJob(mail);
        }
    }

    @Override
    public void sendHtmlMail(String toUser, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(yumailServerProperties.getMail().getUsername());
            helper.setTo(toUser);
            helper.setSubject(subject);
            helper.setText(content, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
        logService.info(yumailServerProperties.getAppId(), LogTemplate.sendMailTemplate(yumailServerProperties.getAppId(), yumailServerProperties.getMail().getUsername(), toUser, content));
    }

    private void startSendMailJob(Mail mail) {
        Map<String, Object> params = new HashMap<>(16);
        params.put("mail", mail);
        String uniKey = UUID.randomUUID().toString();
        // 组设置为调度源appId
        quartzService.addJob("sendMail-" + uniKey, mail.getSource(), "sendMail-" + uniKey, mail.getSource(), LocalSendMailJob.class,
                mail.getCrontab(), mail.getStartTime(), mail.getEndTime(), params);
    }

}
