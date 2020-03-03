package com.dashboard.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Hamza.Ouni
 */
@Component
public class KafkaRestProxyConfiguration {

    @Value("${kafka.rest.proxy.host}")
    private String host;

    @Value("${kafka.rest.proxy.port}")
    private String port;

    @Value("${kafka.topic}")
    private String topic;

    public String getKafkaRestProxyUrl(){
        System.out.println("url =http://"+host+":"+port+"/topics/"+topic);
        return "http://"+host+":"+port+"/topics/"+topic;
    }
}
