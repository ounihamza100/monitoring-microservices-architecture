package com.dashboard;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class KafkaStreamsEurekaClientsArrangementApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KafkaStreamsEurekaClientsArrangementApplication.class, args);
    }

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
                .mapValues(value -> affiche(value))
                ;

        kStreamProcessor.to(kafkaTopicSend);

        KafkaStreams streams = new KafkaStreams(builder, config);

        streams.cleanUp(); // only do this in dev - not in prod

        streams.start();

        // shutdown hook to correctly close the streams application

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }

    private String affiche(String st) {
        if (!st.equals(""))
            System.out.println("\n*******************\n" + st + "\n*******************\n");
        else
            System.out.println("\n******************* value is empty *******************\n");
        return st;
    }
}
