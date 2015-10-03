package org.lovepacs.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserJson {

    @JsonProperty private Integer id;
    @JsonProperty private String name;
    @JsonProperty private String password;
    @JsonProperty private Boolean enabled;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() { return password; }

    public Boolean getEnabled() {
        return enabled;
    }
}
