package org.lovepacs.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @version 1.0
 */
@Entity
@Table(name = "contents")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer boxId;

    @NotNull
    private Integer itemId;

    @NotNull
    private Integer quantity;

    public Content() {}


    public Content(Integer boxId, Integer itemId, Integer quantity) {

        this.boxId = boxId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
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
