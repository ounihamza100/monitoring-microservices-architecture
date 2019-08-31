package com.dashboard.kafkaSender;

import com.dashboard.dtos.ListApplicationMetadata;

public interface KafkaRestProxySender {

    void sendListApplicationMetadata(ListApplicationMetadata listApplicationMetadata);
}
