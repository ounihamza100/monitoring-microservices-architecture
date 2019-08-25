package com.dashboard.controller;

import com.dashboard.dto.StringResponse;
import com.netflix.appinfo.ApplicationInfoManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by haithem.ben-chaaben on 15/01/2019.
 */
@RestController
@RequestMapping("/welcome")
public class LoggingBackEndController {
    static final Logger lOGGER = LoggerFactory.getLogger(LoggingBackEndController.class);

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public StringResponse info() {
        lOGGER.info("info log");
        return StringResponse.builder()
                .str("welcome in log info , " + String.valueOf(ApplicationInfoManager.getInstance().getInfo().getPort())+" was responded")
                .build();
    }
    @RequestMapping(value = "/debug", method = RequestMethod.GET)
    public StringResponse debug() {
        lOGGER.debug("debug log");
        return StringResponse.builder()
                .str("welcome in log debug , " + String.valueOf(ApplicationInfoManager.getInstance().getInfo().getPort())+" was responded")
                .build();
    }

    @RequestMapping(value = "/error/01", method = RequestMethod.GET)
    public StringResponse error_01() {
        String str = String.valueOf(ApplicationInfoManager.getInstance().getInfo().getPort());
        lOGGER.error("error log 01");
        return StringResponse.builder()
                .str("welcome in error log 01 , " + String.valueOf(ApplicationInfoManager.getInstance().getInfo().getPort())+" was responded")
                .build();
    }


    @RequestMapping(value = "/error/02", method = RequestMethod.GET)
    public StringResponse error_02() {
        String str = String.valueOf(ApplicationInfoManager.getInstance().getInfo().getPort());
        lOGGER.error("error log 02");
        return StringResponse.builder()
                .str("welcome in error log 02 , " + String.valueOf(ApplicationInfoManager.getInstance().getInfo().getPort())+" was responded")
                .build();
    }


    @RequestMapping(value = "/error/03", method = RequestMethod.GET)
    public StringResponse error_03() {
        String str = String.valueOf(ApplicationInfoManager.getInstance().getInfo().getPort());
        lOGGER.error("error log 03");
        return StringResponse.builder()
                .str("welcome in error log 03 , " + String.valueOf(ApplicationInfoManager.getInstance().getInfo().getPort())+" was responded")
                .build();
    }

    @RequestMapping(value = "/no", method = RequestMethod.GET)
    public StringResponse no() {
        return StringResponse.builder()
                .str("welcome in no log , " + String.valueOf(ApplicationInfoManager.getInstance().getInfo().getPort())+" was responded")
                .build();
    }
}
