package com.dashboard.kafkaSender;

import com.dashboard.dtos.ApplicationMetadata;
import com.dashboard.dtos.ListApplicationMetadata;
import com.dashboard.models.receiver.KafkaResponse;
import com.dashboard.models.sender.KafkaRecord;
import com.dashboard.models.sender.Record;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaRestProxySenderImpl implements KafkaRestProxySender {

    public static final String MEDIA_TYPE_VALUE = "application/vnd.kafka.json.v2+json";

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${kafka.topic.applications.metadatas}")
    private String kafkaTopicApplicationsMetadatas;

    @Value("${kafka.rest.proxy.host}")
    private String kafkaHost;

    @Value("${kafka.rest.proxy.port}")
    private String kafkaPort;

    @Override
    public void sendListApplicationMetadata(ListApplicationMetadata listApplicationMetadata) {
        String str = "";
        KafkaRecord kafkaRecord = new KafkaRecord();
        List<Record> records = new ArrayList<>();
        try {
        for(ApplicationMetadata app : listApplicationMetadata.getList()) {

            str = mapper.writeValueAsString(app);
            Record record = new Record();
            record.setValue(str);
            record.setKey(app.getMicroServiceName());
            records.add(record);
        }
            kafkaRecord.setRecords(records);
            String rec = mapper.writeValueAsString(kafkaRecord);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(MEDIA_TYPE_VALUE));

            HttpEntity<String> httpEntity = new HttpEntity<>(rec, headers);
            ResponseEntity<KafkaResponse> response = restTemplate.exchange(getFullUrl(kafkaTopicApplicationsMetadatas), HttpMethod.POST, httpEntity, KafkaResponse.class);

        System.out.println("\n################### \n " + rec + "\n ###################\n");

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public String getFullUrl(String topic) {
        return "http://" + kafkaHost + ":" + kafkaPort + "/topics/" + topic;
    }
}
