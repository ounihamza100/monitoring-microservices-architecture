package com.dashboard;

import com.dashboard.processor.ProcessorService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.Arrays;

@SpringBootApplication
public class KafkaConsumerProducerEurekaClientsArrangementApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerProducerEurekaClientsArrangementApplication.class, args);
    }

    @Autowired
    private KafkaConsumer<String, String> kafkaConsumer;

    @Autowired
    private ProcessorService processorService;

    @Value("${kafka.topic.consume}")
    private String kafkaTopicConsume;

    @Override
    public void run(String... strings) throws Exception {

        kafkaConsumer.subscribe(Arrays.asList(kafkaTopicConsume));

        while (true) {

            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> events : records) {

                System.out.println("============= start =================");

                processorService.process(events.value());

                System.out.println("============= end =================");

            }
        }
    }
}
