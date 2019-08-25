package com.dashboard.models;

import lombok.*;

/**
 * Created by haithem.ben-chaaben on 13/03/2019.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class LogModel {
    private String name;
    private String port;
    private String className;
    private String method;
    private String level;
    private String message;
    private Long dateLog;
    private String lisibleDate;
}
