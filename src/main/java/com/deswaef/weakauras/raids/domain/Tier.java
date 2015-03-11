package com.deswaef.weakauras.raids.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tier")
public class Tier {

    @Id
    private Long id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "tier")
    private Set<Raid> raids;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Raid> getRaids() {
        return raids;
    }

    public void setRaids(Set<Raid> raids) {
        this.raids = raids;
    }
}
