package com.dashboard;

import com.dashboard.dao.ElasticRepository;
import com.dashboard.mapper.LogMapper;
import com.dashboard.model.LogModel;
import com.dashboard.model.LogModelToElastic;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.Arrays;

@SpringBootApplication
public class KafkaConsumerGroupErrorLogsElasticsearchSenderApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerGroupErrorLogsElasticsearchSenderApplication.class, args);
    }

    @Autowired
    private KafkaConsumer<String, String> kafkaConsumer;
    @Autowired
    private ElasticRepository elasticRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public void run(String... args) throws Exception {
        String topicName = "errors";
        kafkaConsumer.subscribe(Arrays.asList(topicName));

        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> events : records) {

                System.out.println("\n /////// start ///////\n "
                + "key = " + events.key()
                        + "\n partition  = " + events.partition()
                +"\n Offset = "+ events.offset()
                +"\n");
                System.out.println("==============================");
                String aux = events.value().substring(1, events.value().length() - 1).replace("\\", "");
                //System.out.println(aux);
                LogModel logging = mapper.readValue(aux, LogModel.class);
                LogModelToElastic elastic = LogMapper.modelToElastic(logging);
                System.out.println(elastic);
                elasticRepository.save(elastic);
                System.out.println("==============================");
            }
        }
    }

}
