package com.deswaef.wowscrappie.classes.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    @OneToMany(mappedBy = "wowClass", cascade = CascadeType.REMOVE)
    private Set<Spec> specs;

    public Long getId() {
        return id;
    }

    public WowClass setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getWarcraftlogsId() {
        return warcraftlogsId;
    }

    public WowClass setWarcraftlogsId(Long warcraftlogsId) {
        this.warcraftlogsId = warcraftlogsId;
        return this;
    }

    public String getName() {
        return name;
    }

    public WowClass setName(String name) {
        this.name = name;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public WowClass setSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public Set<Spec> getSpecs() {
        return specs;
    }

    public WowClass setSpecs(Set<Spec> specs) {
        this.specs = specs;
        return this;
    }
}
