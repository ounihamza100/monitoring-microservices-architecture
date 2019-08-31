package com.dashboard.kafkaSender;

import com.dashboard.dtos.ListApplicationMetadata;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaRestProxySenderImpl implements KafkaRestProxySender {
    @Autowired
    private ObjectMapper mapper;

    @Override
    public void sendListApplicationMetadata(ListApplicationMetadata listApplicationMetadata) {
        String str = "null";
        try {
            str = mapper.writeValueAsString(listApplicationMetadata);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("\n################### \n " + str + "\n ###################\n");
    }
}
