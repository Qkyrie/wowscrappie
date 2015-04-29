package com.deswaef.weakauras.classes.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "wowclass")
public class WowClass {

    @Id
    private Long id;

    @Column(name = "wcl_id")
    private Long warcraftlogsId;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @OneToMany(mappedBy = "wowClass")
    private Set<Spec> specs;

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Set<Spec> getSpecs() {
        return specs;
    }

    public void setSpecs(Set<Spec> specs) {
        this.specs = specs;
    }

    public Long getWarcraftlogsId() {
        return warcraftlogsId;
    }

    public WowClass setWarcraftlogsId(Long warcraftlogsId) {
        this.warcraftlogsId = warcraftlogsId;
        return this;
    }
}
