package com.deswaef.weakauras.classes.domain;

import javax.persistence.*;

@Entity
@Table(name = "wowspec")
public class Spec {

    @Id
    private Long id;

    @Column(name = "wcl_id")
    private Long warcraftlogsId;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @ManyToOne
    @JoinColumn(name = "wowclass_id")
    private WowClass wowClass;

    public Long getId() {
        return id;
    }

    public Spec setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getWarcraftlogsId() {
        return warcraftlogsId;
    }

    public Spec setWarcraftlogsId(Long warcraftlogsId) {
        this.warcraftlogsId = warcraftlogsId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Spec setName(String name) {
        this.name = name;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public Spec setSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public WowClass getWowClass() {
        return wowClass;
    }

    public Spec setWowClass(WowClass wowClass) {
        this.wowClass = wowClass;
        return this;
    }
}
