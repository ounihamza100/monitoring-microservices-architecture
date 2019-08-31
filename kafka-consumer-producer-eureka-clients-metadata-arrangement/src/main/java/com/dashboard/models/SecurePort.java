
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
    "$",
    "@enabled"
})
public class SecurePort {

    @JsonProperty("$")
    private Integer $;
    @JsonProperty("@enabled")
    private String enabled;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("$")
    public Integer get$() {
        return $;
    }

    @JsonProperty("$")
    public void set$(Integer $) {
        this.$ = $;
    }

    @JsonProperty("@enabled")
    public String getEnabled() {
        return enabled;
    }

    @JsonProperty("@enabled")
    public void setEnabled(String enabled) {
        this.enabled = enabled;
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
