package com.dhu.yumail.server.conf;

import com.dhu.yumail.server.properties.YumailServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.annotation.Resource;

/**
 * JavaMailSender配置类
 * @author Yupi Li
 * @date 2019-04-16 15:13
 */
@Configuration
public class MailSenderConfig {


    @Resource
    private YumailServerProperties yumailServerProperties;

    @Bean
    public JavaMailSender javaMailSender() {
        YumailServerProperties.MailProperties mailProperties = yumailServerProperties.getMail();
        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
        javaMailSenderImpl.setHost(mailProperties.getHost());
        javaMailSenderImpl.setUsername(mailProperties.getUsername());
        javaMailSenderImpl.setPassword(mailProperties.getPassword());
        javaMailSenderImpl.setDefaultEncoding("utf-8");
        return javaMailSenderImpl;
    }
}
