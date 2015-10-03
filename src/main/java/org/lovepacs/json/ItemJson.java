package org.lovepacs.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemJson {

    @JsonProperty private Integer id;
    @JsonProperty private String name;
    @JsonProperty private Boolean enabled;
    @JsonProperty private BigDecimal price;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
