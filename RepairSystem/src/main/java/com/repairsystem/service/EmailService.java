package com.repairsystem.service;

import org.springframework.mail.SimpleMailMessage;

/**
 * @author youminglan
 * @date 2021/04/22
 * @time 10:44
 */
public interface EmailService {

    String acceptOrderMail(String userName,String userEmail);

    String completeOrderMail(String userName,String userEmail);
}
