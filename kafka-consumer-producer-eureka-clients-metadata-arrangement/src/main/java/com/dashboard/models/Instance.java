
package com.dashboard.models;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "instanceId",
    "hostName",
    "app",
    "ipAddr",
    "status",
    "overriddenStatus",
    "port",
    "securePort",
    "countryId",
    "dataCenterInfo",
    "leaseInfo",
    "metadata",
    "homePageUrl",
    "statusPageUrl",
    "healthCheckUrl",
    "vipAddress",
    "secureVipAddress",
    "isCoordinatingDiscoveryServer",
    "lastUpdatedTimestamp",
    "lastDirtyTimestamp",
    "actionType"
})
public class Instance {

    @JsonProperty("instanceId")
    private String instanceId;
    @JsonProperty("hostName")
    private String hostName;
    @JsonProperty("app")
    private String app;
    @JsonProperty("ipAddr")
    private String ipAddr;
    @JsonProperty("status")
    private String status;
    @JsonProperty("overriddenStatus")
    private String overriddenStatus;
    @JsonProperty("port")
    private Port port;
    @JsonProperty("securePort")
    private SecurePort securePort;
    @JsonProperty("countryId")
    private Integer countryId;
    @JsonProperty("dataCenterInfo")
    private DataCenterInfo dataCenterInfo;
    @JsonProperty("leaseInfo")
    private LeaseInfo leaseInfo;
    @JsonProperty("metadata")
    private Metadata metadata;
    @JsonProperty("homePageUrl")
    private String homePageUrl;
    @JsonProperty("statusPageUrl")
    private String statusPageUrl;
    @JsonProperty("healthCheckUrl")
    private String healthCheckUrl;
    @JsonProperty("vipAddress")
    private String vipAddress;
    @JsonProperty("secureVipAddress")
    private String secureVipAddress;
    @JsonProperty("isCoordinatingDiscoveryServer")
    private String isCoordinatingDiscoveryServer;
    @JsonProperty("lastUpdatedTimestamp")
    private String lastUpdatedTimestamp;
    @JsonProperty("lastDirtyTimestamp")
    private String lastDirtyTimestamp;
    @JsonProperty("actionType")
    private String actionType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("instanceId")
    public String getInstanceId() {
        return instanceId;
    }

    @JsonProperty("instanceId")
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @JsonProperty("hostName")
    public String getHostName() {
        return hostName;
    }

    @JsonProperty("hostName")
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @JsonProperty("app")
    public String getApp() {
        return app;
    }

    @JsonProperty("app")
    public void setApp(String app) {
        this.app = app;
    }

    @JsonProperty("ipAddr")
    public String getIpAddr() {
        return ipAddr;
    }

    @JsonProperty("ipAddr")
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("overriddenStatus")
    public String getOverriddenStatus() {
        return overriddenStatus;
    }

    @JsonProperty("overriddenStatus")
    public void setOverriddenStatus(String overriddenStatus) {
        this.overriddenStatus = overriddenStatus;
    }

    @JsonProperty("port")
    public Port getPort() {
        return port;
    }

    @JsonProperty("port")
    public void setPort(Port port) {
        this.port = port;
    }

    @JsonProperty("securePort")
    public SecurePort getSecurePort() {
        return securePort;
    }

    @JsonProperty("securePort")
    public void setSecurePort(SecurePort securePort) {
        this.securePort = securePort;
    }

    @JsonProperty("countryId")
    public Integer getCountryId() {
        return countryId;
    }

    @JsonProperty("countryId")
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    @JsonProperty("dataCenterInfo")
    public DataCenterInfo getDataCenterInfo() {
        return dataCenterInfo;
    }

    @JsonProperty("dataCenterInfo")
    public void setDataCenterInfo(DataCenterInfo dataCenterInfo) {
        this.dataCenterInfo = dataCenterInfo;
    }

    @JsonProperty("leaseInfo")
    public LeaseInfo getLeaseInfo() {
        return leaseInfo;
    }

    @JsonProperty("leaseInfo")
    public void setLeaseInfo(LeaseInfo leaseInfo) {
        this.leaseInfo = leaseInfo;
    }

    @JsonProperty("metadata")
    public Metadata getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    @JsonProperty("homePageUrl")
    public String getHomePageUrl() {
        return homePageUrl;
    }

    @JsonProperty("homePageUrl")
    public void setHomePageUrl(String homePageUrl) {
        this.homePageUrl = homePageUrl;
    }

    @JsonProperty("statusPageUrl")
    public String getStatusPageUrl() {
        return statusPageUrl;
    }

    @JsonProperty("statusPageUrl")
    public void setStatusPageUrl(String statusPageUrl) {
        this.statusPageUrl = statusPageUrl;
    }

    @JsonProperty("healthCheckUrl")
    public String getHealthCheckUrl() {
        return healthCheckUrl;
    }

    @JsonProperty("healthCheckUrl")
    public void setHealthCheckUrl(String healthCheckUrl) {
        this.healthCheckUrl = healthCheckUrl;
    }

    @JsonProperty("vipAddress")
    public String getVipAddress() {
        return vipAddress;
    }

    @JsonProperty("vipAddress")
    public void setVipAddress(String vipAddress) {
        this.vipAddress = vipAddress;
    }

    @JsonProperty("secureVipAddress")
    public String getSecureVipAddress() {
        return secureVipAddress;
    }

    @JsonProperty("secureVipAddress")
    public void setSecureVipAddress(String secureVipAddress) {
        this.secureVipAddress = secureVipAddress;
    }

    @JsonProperty("isCoordinatingDiscoveryServer")
    public String getIsCoordinatingDiscoveryServer() {
        return isCoordinatingDiscoveryServer;
    }

    @JsonProperty("isCoordinatingDiscoveryServer")
    public void setIsCoordinatingDiscoveryServer(String isCoordinatingDiscoveryServer) {
        this.isCoordinatingDiscoveryServer = isCoordinatingDiscoveryServer;
    }

    @JsonProperty("lastUpdatedTimestamp")
    public String getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }

    @JsonProperty("lastUpdatedTimestamp")
    public void setLastUpdatedTimestamp(String lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }

    @JsonProperty("lastDirtyTimestamp")
    public String getLastDirtyTimestamp() {
        return lastDirtyTimestamp;
    }

    @JsonProperty("lastDirtyTimestamp")
    public void setLastDirtyTimestamp(String lastDirtyTimestamp) {
        this.lastDirtyTimestamp = lastDirtyTimestamp;
    }

    @JsonProperty("actionType")
    public String getActionType() {
        return actionType;
    }

    @JsonProperty("actionType")
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
