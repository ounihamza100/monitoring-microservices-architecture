package com.dashboard.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "dashboard", type = "eureka_clients_metadata")
public class EurekaClientsLastMetadata {

    @Id
    private String instanceId;

    private String applicationName;

    private String hostName;

    private String app;

    private String ipAddr;

    private String status;

    private String overriddenStatus;

    private Integer countryId;

    //    lease info start

    private Integer renewalIntervalInSecs;
    private Integer durationInSecs;
    private String registrationTimestamp;
    private String lastRenewalTimestamp;
    private Integer evictionTimestamp;
    private String serviceUpTimestamp;

    // end Lease info start

    private String homePageUrl;

    private String statusPageUrl;

    private String healthCheckUrl;

    private String vipAddress;

    private String secureVipAddress;

    private String isCoordinatingDiscoveryServer;

    private String lastUpdatedTimestamp;

    private String lastDirtyTimestamp;

    private String actionType;

    private Boolean isNewInstance;

}
