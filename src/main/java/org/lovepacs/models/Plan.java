package org.lovepacs.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "plans")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "location")
    private Integer locationId;

    @NotNull
    @Column(name = "box")
    private Integer boxId;

    @NotNull
    private Integer quantity;

    @NotNull
    @Column(name = "packdate")
    private Date packDate;

    @NotNull
    private Boolean enabled;

    public Plan() {}

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getBoxId() {
        return boxId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Boolean getEnabled() { return enabled; }

    public Date getPackDate() { return packDate; }
}