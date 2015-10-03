package org.lovepacs.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationJson {

    @JsonProperty private Integer id;
    @JsonProperty private String name;
    @JsonProperty private Boolean enabled;
    @JsonProperty private List<InventoryJson> inventory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<InventoryJson> getInventory() {
        return inventory;
    }

    public void setInventory(List<InventoryJson> inventory) {
        this.inventory = inventory;
    }
}
