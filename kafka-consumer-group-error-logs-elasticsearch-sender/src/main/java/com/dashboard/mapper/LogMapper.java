package com.dashboard.mapper;

import com.dashboard.model.LogModel;
import com.dashboard.model.LogModelToElastic;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * Hamza.Ouni
 */
public class LogMapper {
    public static LogModelToElastic modelToElastic(LogModel model) {
        Long ts= Timestamp.from(Instant.now()).getTime();
        String s=model.getName();
        s=s.replace("_01","");
        LogModelToElastic elastic=LogModelToElastic.builder()

                .name(s)
                .port(model.getPort())
                .className(model.getClassName())
                .method(model.getMethod())
                .level(model.getLevel())
                .message(model.getMessage())
                .dateLog(model.getDateLog())
                .lisibleDate(model.getLisibleDate())
                .dateCreation(ts)
                .build();
        return elastic;
    }
}
