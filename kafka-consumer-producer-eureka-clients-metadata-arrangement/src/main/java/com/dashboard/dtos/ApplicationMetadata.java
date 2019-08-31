package com.dashboard.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationMetadata {

    private String microServiceName;

    private Boolean isNew;

    private Boolean isRemoved;

    private Long currentDownInstanceNumber;

    private Long currentUpInstanceNumber;

    private Long oldDownInstanceNumber;

    private Long oldUpInstanceNumber;

    private Date eventDate;

}
