package org.lovepacs.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @version 1.0
 */
@Entity
@Table(name = "boxes")
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Boolean enabled;

    public Box() {}

    public Box(String name, Boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
