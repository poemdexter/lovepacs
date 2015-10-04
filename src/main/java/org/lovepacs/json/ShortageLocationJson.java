package org.lovepacs.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShortageLocationJson {

    @JsonProperty private String name;
    @JsonProperty private List<ShortageItemJson> items;


    public ShortageLocationJson(String name, List<ShortageItemJson> items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShortageItemJson> getItems() {
        return items;
    }

    public void setItems(List<ShortageItemJson> items) {
        this.items = items;
    }
}
