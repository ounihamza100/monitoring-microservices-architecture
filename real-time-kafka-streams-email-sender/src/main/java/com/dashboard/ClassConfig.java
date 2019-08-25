package com.dashboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by haithem.ben-chaaben on 8/14/2019.
 */
@Configuration
public class ClassConfig {

    @Value("${send.to}")
    private String sendTo;

    @Value("${subject}")
    private String subject;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public SimpleMailMessage simpleMailMessage(){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(sendTo);

        message.setSubject(subject);

        return message;
    }
}
