package com.deswaef.weakauras.raids.domain;

import com.deswaef.weakauras.ui.weakauras.domain.BossFightWeakAura;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "boss")
@DiscriminatorColumn(name = "boss_type")
public class Boss {

    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "slug")
    private String slug;

    @OneToMany(mappedBy = "boss")
    private List<BossFightWeakAura> bossFightWeakAura = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Boss setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Boss setName(String name) {
        this.name = name;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public Boss setSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public List<BossFightWeakAura> getBossFightWeakAura() {
        return bossFightWeakAura;
    }

    public void setBossFightWeakAura(List<BossFightWeakAura> bossFightWeakAura) {
        this.bossFightWeakAura = bossFightWeakAura;
    }
}
