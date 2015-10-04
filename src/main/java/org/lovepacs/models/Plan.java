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
    @Column(name = "packdate")
    private Date packDate;

    @NotNull
    private Boolean enabled;

    public Plan() {}

    public Plan(Integer id, Integer locationId, Date packDate) {
        this.id = id;
        this.locationId = locationId;
        this.packDate = packDate;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getId() { return id; }

    public Integer getLocationId() {
        return locationId;
    }

    public Boolean getEnabled() { return enabled; }

    public Date getPackDate() { return packDate; }
}