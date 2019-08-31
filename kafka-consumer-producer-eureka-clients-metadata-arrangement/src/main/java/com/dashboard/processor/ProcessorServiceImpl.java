package com.dashboard.processor;

import com.dashboard.dtos.ApplicationMetadata;
import com.dashboard.dtos.ListApplicationMetadata;
import com.dashboard.entities.EurekaClientsLastMetadata;
import com.dashboard.kafkaSender.KafkaRestProxySender;
import com.dashboard.mapper.EurekaClientsMetadataMapper;
import com.dashboard.models.Application;
import com.dashboard.models.EurekaClientsMetadata;
import com.dashboard.models.Instance;
import com.dashboard.repositories.ElasticRepository;
import com.dashboard.utils.Utility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProcessorServiceImpl implements ProcessorService {

    private static String UP="UP";

    private static String DOWN="DOWN";

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ElasticRepository elasticRepository;
    @Autowired
    private KafkaRestProxySender kafkaRestProxySender;

    @Override
    public void process(String stream) {
        if (Utility.isNotEmptyAndNotNull(stream)) {
            //showStream(stream);
            EurekaClientsMetadata eurekaClientsMetadata = mapStreamToModel(stream);
            startProcess(eurekaClientsMetadata);
            saveNewMetaDataToElastic(eurekaClientsMetadata);
        }
    }

    // ########################################################################################

    private void showStream(String str) {
        System.out.println("\n*******************\n" + str + "\n*******************\n");
    }
    // ########################################################################################

    private void startProcess(EurekaClientsMetadata metadatas) {

        List<ApplicationMetadata> applicationMetadataList = new ArrayList<>();

        for (Application application : metadatas.getApplications().getApplication()) {

            List<EurekaClientsLastMetadata> lastMetadataList = elasticRepository.findAllByApplicationName(application.getName());

            ApplicationMetadata applicationMetadata = determinateUpDownInstance(application);
            applicationMetadata.setMicroServiceName(application.getName());
            applicationMetadata.setIsRemoved(Boolean.FALSE);
            applicationMetadata.setEventDate(new Date());
            applicationMetadata.setIsNew((lastMetadataList.isEmpty() ? Boolean.TRUE : Boolean.FALSE));
            applicationMetadata.setOldDownInstanceNumber(elasticRepository.countAllByApplicationNameAndStatus(application.getName(), DOWN));
            applicationMetadata.setOldUpInstanceNumber(elasticRepository.countAllByApplicationNameAndStatus(application.getName(), UP));
            applicationMetadataList.add(applicationMetadata);
        }
        kafkaRestProxySender.sendListApplicationMetadata(ListApplicationMetadata.builder()
                .list(applicationMetadataList)
                .build());
    }
    // ########################################################################################

    private EurekaClientsMetadata mapStreamToModel(String str) {
        EurekaClientsMetadata metadata = null;
        try {
            metadata = mapper.readValue(str.substring(1, str.length() - 1).replace("\\", ""), EurekaClientsMetadata.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return metadata;
    }

    // ########################################################################################

    private ApplicationMetadata determinateUpDownInstance(Application app) {
        Long up = 0L;
        Long down = 0L;
        for (Instance item : app.getInstance()) {
            if (item.getStatus().equals(UP))
                up++;
            else down++;
        }
        return ApplicationMetadata.builder().currentDownInstanceNumber(down).currentUpInstanceNumber(up).build();
    }

// ########################################################################################


    private void saveNewMetaDataToElastic(EurekaClientsMetadata clientsMetadata) {
        elasticRepository.saveAll(EurekaClientsMetadataMapper.mapToEntities(clientsMetadata.getApplications()));
    }

    // ########################################################################################
}
