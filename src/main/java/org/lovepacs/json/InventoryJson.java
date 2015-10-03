package org.lovepacs.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InventoryJson {

    @JsonProperty private Integer id;
    @JsonProperty private Integer itemId;
    @JsonProperty private Integer quantity;

    public Integer getId() {
        return id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
