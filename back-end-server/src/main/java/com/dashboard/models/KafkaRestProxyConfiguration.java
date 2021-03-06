package com.dashboard.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Hamza.Ouni
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
