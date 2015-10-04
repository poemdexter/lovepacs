package org.lovepacs.json;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InventoryNeedJson {

    @JsonProperty private String name;
    @JsonProperty private List<ShortageJson> items;

    public void setItems(List<ShortageJson> items) {
        this.items = items;
    }

    public void setName(String name) {
        this.name = name;
    }
}
