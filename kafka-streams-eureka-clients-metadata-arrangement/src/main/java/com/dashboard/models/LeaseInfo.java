
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
    "renewalIntervalInSecs",
    "durationInSecs",
    "registrationTimestamp",
    "lastRenewalTimestamp",
    "evictionTimestamp",
    "serviceUpTimestamp"
})
public class LeaseInfo {

    @JsonProperty("renewalIntervalInSecs")
    private Integer renewalIntervalInSecs;
    @JsonProperty("durationInSecs")
    private Integer durationInSecs;
    @JsonProperty("registrationTimestamp")
    private String registrationTimestamp;
    @JsonProperty("lastRenewalTimestamp")
    private String lastRenewalTimestamp;
    @JsonProperty("evictionTimestamp")
    private Integer evictionTimestamp;
    @JsonProperty("serviceUpTimestamp")
    private String serviceUpTimestamp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("renewalIntervalInSecs")
    public Integer getRenewalIntervalInSecs() {
        return renewalIntervalInSecs;
    }

    @JsonProperty("renewalIntervalInSecs")
    public void setRenewalIntervalInSecs(Integer renewalIntervalInSecs) {
        this.renewalIntervalInSecs = renewalIntervalInSecs;
    }

    @JsonProperty("durationInSecs")
    public Integer getDurationInSecs() {
        return durationInSecs;
    }

    @JsonProperty("durationInSecs")
    public void setDurationInSecs(Integer durationInSecs) {
        this.durationInSecs = durationInSecs;
    }

    @JsonProperty("registrationTimestamp")
    public String getRegistrationTimestamp() {
        return registrationTimestamp;
    }

    @JsonProperty("registrationTimestamp")
    public void setRegistrationTimestamp(String registrationTimestamp) {
        this.registrationTimestamp = registrationTimestamp;
    }

    @JsonProperty("lastRenewalTimestamp")
    public String getLastRenewalTimestamp() {
        return lastRenewalTimestamp;
    }

    @JsonProperty("lastRenewalTimestamp")
    public void setLastRenewalTimestamp(String lastRenewalTimestamp) {
        this.lastRenewalTimestamp = lastRenewalTimestamp;
    }

    @JsonProperty("evictionTimestamp")
    public Integer getEvictionTimestamp() {
        return evictionTimestamp;
    }

    @JsonProperty("evictionTimestamp")
    public void setEvictionTimestamp(Integer evictionTimestamp) {
        this.evictionTimestamp = evictionTimestamp;
    }

    @JsonProperty("serviceUpTimestamp")
    public String getServiceUpTimestamp() {
        return serviceUpTimestamp;
    }

    @JsonProperty("serviceUpTimestamp")
    public void setServiceUpTimestamp(String serviceUpTimestamp) {
        this.serviceUpTimestamp = serviceUpTimestamp;
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
