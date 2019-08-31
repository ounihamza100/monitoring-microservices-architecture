package com.dashboard.repositories;

import com.dashboard.entities.EurekaClientsLastMetadata;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ElasticRepository extends ElasticsearchRepository<EurekaClientsLastMetadata, String> {

    List<EurekaClientsLastMetadata> findAllByApplicationName(String applicationName);

    Long countAllByApplicationNameAndStatus(String applivationName,String status);
}
