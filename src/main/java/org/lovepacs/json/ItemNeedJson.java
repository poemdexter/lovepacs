package org.lovepacs.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemNeedJson {

    @JsonProperty private String name;
    @JsonProperty private Integer quantity;

    public ItemNeedJson(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
