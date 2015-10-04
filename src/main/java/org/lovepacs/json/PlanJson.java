package org.lovepacs.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanJson {

    @JsonProperty private Integer id;
    @JsonProperty private Integer location;
    @JsonProperty private List<PlanBoxJson> planBoxes;
    @JsonProperty private Date packDate;
    @JsonProperty private Boolean enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public List<PlanBoxJson> getPlanBoxes() {
        return planBoxes;
    }

    public void setPlanBoxes(List<PlanBoxJson> box) {
        this.planBoxes = planBoxes;
    }

    public Date getPackDate() {
        return packDate;
    }

    public void setPackDate(Date packDate) {
        this.packDate = packDate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}