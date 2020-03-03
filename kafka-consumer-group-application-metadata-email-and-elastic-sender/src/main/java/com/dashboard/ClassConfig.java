package com.dashboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Hamza.Ouni
 */
@Configuration
public class ClassConfig {

    @Value("${application.id.config}")
    private String applicationIdConfig;
    @Value("${auto.offset.reset.config}")
    private String autoOffsetResetConfig;

    @Value("${bootstrap.server.config}")
    private String bootstrapServerConfig;

    @Bean
    public Properties properties() {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServerConfig);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetResetConfig);
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, applicationIdConfig);
        return properties;
    }

    @Bean(name = "kafkaConsumer")
    public KafkaConsumer<String, String> kafkaConsumer() {

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties());
        return consumer;
    }

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }
}
