package org.lovepacs.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShortageJson {

    @JsonProperty private String locationName;
    @JsonProperty private String itemName;
    @JsonProperty private Integer shortageAmount;

    public ShortageJson(String locationName, String itemName, Integer shortageAmount) {
        this.locationName = locationName;
        this.itemName = itemName;
        this.shortageAmount = shortageAmount;
    }
}
