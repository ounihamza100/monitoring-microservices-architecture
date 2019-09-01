package com.dashboard;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class KafkaConsumerGroupApplicationMetadataEmailAndElasticSenderApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerGroupApplicationMetadataEmailAndElasticSenderApplication.class, args);
    }

    @Autowired
    private KafkaConsumer<String, String> kafkaConsumer;

    @Autowired
    private ObjectMapper mapper;

    @Value("${kafka.topic.consume}")
    private String kafkaTopicConsume;

    @Override
    public void run(String... args) throws Exception {
        String topicName = kafkaTopicConsume;
        kafkaConsumer.subscribe(Arrays.asList(topicName));

        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> events : records) {

                System.out.println("==============================");
                System.out.println("\n /////// start ///////\n "
                        + "key = " + events.key()
                        + "\n partition  = " + events.partition()
                        +"\n Offset = "+ events.offset()
                        +"\n/////// end ///////\n");
                String stream = events.value().substring(1, events.value().length() - 1).replace("\\", "");
                System.out.println(stream);
                System.out.println("==============================");
            }
        }
    }

}
