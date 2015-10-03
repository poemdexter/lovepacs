package org.lovepacs.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationJson {

    @JsonProperty private Integer id;
    @JsonProperty private String name;
    @JsonProperty private List<InventoryJson> inventory;
}
