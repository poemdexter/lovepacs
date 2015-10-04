package org.lovepacs.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "plan_boxes")
public class PlanBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "plan")
    private Integer planId;

    @NotNull
    @Column(name = "box")
    private Integer boxId;

    @NotNull
    private Integer quantity;

    public PlanBox() {}

    public PlanBox(Integer id, Integer planId, Integer boxId, Integer quantity)
    {
        this.id = id;
        this.planId = planId;
        this.boxId = boxId;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
