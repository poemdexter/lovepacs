package org.lovepacs.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventoryLevelJson {

    @JsonProperty private String locationName;
    @JsonProperty private String itemName;
    @JsonProperty private Integer quantity;


}
