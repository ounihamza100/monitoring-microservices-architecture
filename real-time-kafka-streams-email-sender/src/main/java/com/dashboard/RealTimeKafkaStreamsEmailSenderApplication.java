package com.dashboard;

import com.dashboard.service.EmailSenderService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class RealTimeKafkaStreamsEmailSenderApplication implements CommandLineRunner {

    @Autowired
    private EmailSenderService emailSenderService;

    public static void main(String[] args) {
        SpringApplication.run(RealTimeKafkaStreamsEmailSenderApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        Properties config = new Properties();

        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "email-sender-application");

        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");

        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        KStreamBuilder builder = new KStreamBuilder();

        KStream<String, String> kStream = builder.stream("errors");

        KStream<String, String> kStreamProcessor = kStream
                .mapValues(value -> emailSenderService.send(value))
                .filter((key, value) -> !value.equals(""))
                //.mapValues(value -> affiche(value))
                ;

        kStreamProcessor.to("errors_sent_emails");

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
