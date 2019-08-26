package com.dashboard.repositories;

import com.dashboard.entities.EurekaClientsLastMetadata;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticRepository extends ElasticsearchRepository<EurekaClientsLastMetadata, String> {
}
