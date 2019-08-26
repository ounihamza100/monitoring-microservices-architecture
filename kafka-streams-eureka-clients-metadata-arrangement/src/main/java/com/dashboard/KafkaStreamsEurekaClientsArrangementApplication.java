package com.dashboard;

import com.dashboard.entities.EurekaClientsLastMetadata;
import com.dashboard.mapper.EurekaClientsMetadataMapper;
import com.dashboard.models.Application;
import com.dashboard.models.EurekaClientsMetadata;
import com.dashboard.repositories.ElasticRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

@SpringBootApplication
public class KafkaStreamsEurekaClientsArrangementApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KafkaStreamsEurekaClientsArrangementApplication.class, args);
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ElasticRepository elasticRepository;

    @Value("${kafka.topic.consume}")
    private String kafkaTopicConsume;

    @Value("${kafka.topic.send}")
    private String kafkaTopicSend;

    @Value("${application.id.config}")
    private String applicationIdConfig;

    @Value("${auto.offset.reset.config}")
    private String autoOffsetResetConfig;

    @Value("${bootstrap.server.config}")
    private String bootstrapServerConfig;


    @Override
    public void run(String... strings) throws Exception {

        Properties config = new Properties();

        config.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationIdConfig);

        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServerConfig);

        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetResetConfig);

        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        KStreamBuilder builder = new KStreamBuilder();

        KStream<String, String> kStream = builder.stream(kafkaTopicConsume);

        KStream<String, String> kStreamProcessor = kStream
                .mapValues(value -> process(value));

        kStreamProcessor.to(kafkaTopicSend);

        KafkaStreams streams = new KafkaStreams(builder, config);

        streams.cleanUp(); // only do this in dev - not in prod

        streams.start();

        // shutdown hook to correctly close the streams application

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }

    private String process(String st) {
        if (!st.equals("")) {
            System.out.println("\n*******************\n" + st + "\n*******************\n");

            try {

                EurekaClientsMetadata eurekaClientsMetadata =objectMapper.readValue(st.substring(1, st.length() - 1).replace("\\", ""),EurekaClientsMetadata.class);

                elasticRepository.saveAll(EurekaClientsMetadataMapper.mapToEntities(eurekaClientsMetadata.getApplications()));

                System.out.println("\n*******************\n" + eurekaClientsMetadata.getApplications().getApplication().get(0).getName() + "\n*******************\n");

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else
            System.out.println("\n******************* value is empty *******************\n");

        return st;
    }
}
