package org.lovepacs.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserJson {

    @JsonProperty private String name;
    @JsonProperty private String password;
    @JsonProperty private Boolean enabled;

    public String getName() {
        return name;
    }

    public String getPassword() { return password; }

    public Boolean getEnabled() {
        return enabled;
    }
}
