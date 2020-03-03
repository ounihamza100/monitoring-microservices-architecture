package com.dashboard.service;

import com.dashboard.email.EmailSenderTemplate;
import com.dashboard.model.LogModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Hamza.Ouni
 */
@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private EmailSenderTemplate emailSenderTemplate;

    @Override
    public String send(String logContent) {
        return process(logContent);
    }

    private String process(String value) {

        value = value.substring(1, value.length() - 1).replace("\\", "");

        System.out.println("\n ============= \n" + value + "\n ============= \n");

        String str = "";

        if (decide(value))
            str = emailSenderTemplate.send(value);
        return str;
    }

    private Boolean decide(String value) {
        try {

            LogModel log = mapper.readValue(value, LogModel.class);

            if (log.getMethod().equals("error_03") && log.getClassName().equals("com.dashboard.controller.LoggingBackEndController"))

                return true;
            else

                return false;

        } catch (IOException e) {

            e.printStackTrace();

            return false;
        }
    }
}
