package com.dashboard.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by haithem.ben-chaaben on 17/05/2019.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KafkaRestProxyConfiguration {

    private String host;
    private String port;
    private String topic;
}
