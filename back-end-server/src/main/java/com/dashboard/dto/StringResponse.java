package com.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by haithem.ben-chaaben on 16/05/2019.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StringResponse {
    private String str;
}
