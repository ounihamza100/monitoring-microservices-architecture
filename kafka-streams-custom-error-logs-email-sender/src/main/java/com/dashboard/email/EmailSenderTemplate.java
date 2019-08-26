package com.dashboard.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Created by haithem.ben-chaaben on 8/14/2019.
 */
@Component
public class EmailSenderTemplate {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SimpleMailMessage mailMessage;

    public String send(String emailContent){

        mailMessage.setText(emailContent);

        javaMailSender.send(mailMessage);

        return emailContent;
    }
}
