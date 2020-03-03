package com.dashboard.kafka;

import com.dashboard.constants.Constatnts;
import com.dashboard.eureka.EurekaMetadataProvider;
import com.dashboard.models.receiver.KafkaResponse;
import com.dashboard.models.sender.KafkaRecord;
import com.dashboard.models.sender.Record;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Hamza.Ouni
 */
@Component
//@EnableScheduling
public class KafkaHttpRestProxySender implements HttpRestProxySender {

    Logger logger = LoggerFactory.getLogger(KafkaHttpRestProxySender.class);

    @Autowired
    private HttpHeaders httpHeaders;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EurekaMetadataProvider eurekaMetadataProvider;

    @Autowired
    private KafkaRestProxyConfiguration kafkaRestProxyConfiguration;

    @Override
//    @Scheduled(fixedDelay = 5000)
    public void send() {

        logger.info("[ KafkaHttpRestProxySender ] start send");

        try {

            Record record = new Record();

            record.setValue(eurekaMetadataProvider.getMetaDataFromEureka());

            KafkaRecord kafkaRecord = new KafkaRecord();

            List<Record> records = new ArrayList<>();

            records.add(record);

            kafkaRecord.setRecords(records);

            String rec = objectMapper.writeValueAsString(kafkaRecord);

            httpHeaders.setContentType(MediaType.valueOf(Constatnts.MEDIA_TYPE_Value));

            HttpEntity<String> httpEntity = new HttpEntity<>(rec, httpHeaders);

            ResponseEntity<KafkaResponse> response = restTemplate.exchange(kafkaRestProxyConfiguration.getKafkaRestProxyUrl(), HttpMethod.POST, httpEntity, KafkaResponse.class);

        } catch (JsonProcessingException e) {
            logger.error("[ KafkaHttpRestProxySender ] error occured when parsing record object ");
            e.printStackTrace();
        }
        logger.info("[ KafkaHttpRestProxySender ] end");

    }
}
