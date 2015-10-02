package org.lovepacs.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    protected User() {}

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
