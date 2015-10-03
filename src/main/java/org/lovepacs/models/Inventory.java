package org.lovepacs.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "location")
    private Integer locationId;

    @NotNull
    @Column(name = "item")
    private Integer itemId;

    @NotNull
    private Integer quantity;

    public Inventory() {}

    public Inventory(Integer locationId, Integer itemId, Integer quantity) {
        this.locationId = locationId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
