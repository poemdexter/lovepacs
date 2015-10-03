package org.lovepacs.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentJson {

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
