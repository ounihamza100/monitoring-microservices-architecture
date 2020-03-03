package com.dashboard.appenders;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.dashboard.models.KafkaRestProxyConfiguration;
import com.dashboard.models.LogModel;
import com.dashboard.models.receiver.KafkaResponse;
import com.dashboard.models.sender.KafkaRecord;
import com.dashboard.models.sender.Record;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.ApplicationInfoManager;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Hamza.Ouni
 */
public class KafkaRestProxyAppender extends AppenderBase<ILoggingEvent> {

    public static final String MEDIA_TYPE_Value = "application/vnd.kafka.json.v2+json";
    private ObjectMapper mapper = new ObjectMapper();
    private RestTemplate restTemplate = new RestTemplate();
    private KafkaRestProxyConfiguration kafkaRestProxyConfiguration;

    @Override
    public void append(ILoggingEvent event) {

        LogModel logModel = LogModel.builder()
                .dateLog(event.getTimeStamp())
                .className(event.getLoggerName())
                .method(event.getCallerData()[0].getMethodName())
                .level(event.getLevel().toString())
                .lisibleDate(convertDate(event.getTimeStamp()))
                .name(String.valueOf(ApplicationInfoManager.getInstance().getInfo().getAppName()))
                .port(String.valueOf(ApplicationInfoManager.getInstance().getInfo().getPort()))
                .message(event.getMessage())
                .build();
        System.out.println("===================\n" + logModel.toString() + "\n===================\n");
        this.sendToKafka(logModel);
    }

    public void sendToKafka(LogModel logging) {
        try {
            String str = mapper.writeValueAsString(logging);
            Record record = new Record();
            record.setValue(str);
            KafkaRecord kafkaRecord = new KafkaRecord();
            List<Record> records = new ArrayList<>();
            records.add(record);
            kafkaRecord.setRecords(records);
            String rec = mapper.writeValueAsString(kafkaRecord);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(MEDIA_TYPE_Value));

            HttpEntity<String> httpEntity = new HttpEntity<>(rec, headers);
            ResponseEntity<KafkaResponse> response = restTemplate.exchange(this.getFullUrl(), HttpMethod.POST, httpEntity, KafkaResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getFullUrl() {
        return "http://" + kafkaRestProxyConfiguration.getHost() + ":" + kafkaRestProxyConfiguration.getPort() + "/topics/" + kafkaRestProxyConfiguration.getTopic();
    }

    public String convertDate(long tstp) {
        Timestamp stamp = new Timestamp(tstp);
        Date date = new Date(stamp.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        return sdf.format(date);
    }

    public KafkaRestProxyConfiguration getKafkaRestProxyConfiguration() {
        return kafkaRestProxyConfiguration;
    }

    public void setKafkaRestProxyConfiguration(KafkaRestProxyConfiguration kafkaRestProxyConfiguration) {
        this.kafkaRestProxyConfiguration = kafkaRestProxyConfiguration;
    }
}
