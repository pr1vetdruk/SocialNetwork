package ru.privetdruk.socialnetwork.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "city_dbt")
public class City implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

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
}
