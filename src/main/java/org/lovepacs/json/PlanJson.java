package org.lovepacs.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanJson {

    @JsonProperty private Integer id;
    @JsonProperty private Integer location;
    @JsonProperty private BoxJson box;
    @JsonProperty private Integer quantity;
    @JsonProperty private Date packDate;
    @JsonProperty private Boolean enabled;
}