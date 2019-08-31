package com.dashboard.mapper;

import com.dashboard.entities.EurekaClientsLastMetadata;
import com.dashboard.models.Application;
import com.dashboard.models.Applications;
import com.dashboard.models.Instance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EurekaClientsMetadataMapper {

    public static EurekaClientsLastMetadata mapToInstanceEntity(Instance model, String applicationName) {

        return EurekaClientsLastMetadata.builder()

                .instanceId(model.getInstanceId())

                .applicationName(applicationName)

                .hostName(model.getHostName())

                .app(model.getApp())

                .ipAddr(model.getIpAddr())

                .status(model.getStatus())

                .overriddenStatus(model.getOverriddenStatus())

                .countryId(model.getCountryId())

                //    lease info start

                .renewalIntervalInSecs(model.getLeaseInfo().getRenewalIntervalInSecs())
                .durationInSecs(model.getLeaseInfo().getDurationInSecs())
                .registrationTimestamp(model.getLeaseInfo().getRegistrationTimestamp())
                .lastRenewalTimestamp(model.getLeaseInfo().getLastRenewalTimestamp())
                .evictionTimestamp(model.getLeaseInfo().getEvictionTimestamp())
                .serviceUpTimestamp(model.getLeaseInfo().getServiceUpTimestamp())

                // end Lease info start

                .homePageUrl(model.getHomePageUrl())

                .statusPageUrl(model.getStatusPageUrl())

                .healthCheckUrl(model.getHealthCheckUrl())

                .vipAddress(model.getVipAddress())

                .secureVipAddress(model.getSecureVipAddress())

                .isCoordinatingDiscoveryServer(model.getIsCoordinatingDiscoveryServer())

                .lastUpdatedTimestamp(model.getLastUpdatedTimestamp())

                .lastDirtyTimestamp(model.getLastDirtyTimestamp())

                .actionType(model.getActionType())

                .build();

    }

    public static List<EurekaClientsLastMetadata> mapToInstanceEntities(Application application) {
        List<EurekaClientsLastMetadata> list = new ArrayList<>();
        if (application.getInstance() != null && !application.getInstance().isEmpty()) {
            list.addAll(
                    application.getInstance().stream().map(f -> mapToInstanceEntity(f,application.getName())).collect(Collectors.toList()));
        }
        return list;
    }

    public static List<EurekaClientsLastMetadata> mapToEntities(Applications applications) {
        List<EurekaClientsLastMetadata> lastList = new ArrayList<>();
        if (applications.getApplication() != null && !applications.getApplication().isEmpty()) {
            applications.getApplication().forEach(item->lastList.addAll(mapToInstanceEntities(item)));
        }
        return lastList;
    }



}
