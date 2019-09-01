package com.dashboard;

import com.dashboard.kafka.HttpRestProxySender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EurekaMetadataInstanceToKafkaProducerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EurekaMetadataInstanceToKafkaProducerApplication.class, args);
    }

    @Autowired
    private HttpRestProxySender httpRestProxySender;

    @Override
    public void run(String... args) throws Exception {
        httpRestProxySender.send();
    }
}
