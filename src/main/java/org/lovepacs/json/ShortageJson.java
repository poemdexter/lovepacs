package org.lovepacs.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShortageJson {

    @JsonProperty private Integer locationId;
    @JsonProperty private String locationName;
    @JsonProperty private Integer planId;
    @JsonProperty private Integer itemId;
    @JsonProperty private String itemName;
    @JsonProperty private Integer shortageAmount;

    public ShortageJson(Integer locationId, String locationName, Integer planId, Integer itemId, String itemName, Integer shortageAmount) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.planId = planId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.shortageAmount = shortageAmount;
    }
}
