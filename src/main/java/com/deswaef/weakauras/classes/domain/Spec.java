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

    public WowClass getWowClass() {
        return wowClass;
    }

    public void setWowClass(WowClass wowClass) {
        this.wowClass = wowClass;
    }

    public Long getWarcraftlogsId() {
        return warcraftlogsId;
    }

    public Spec setWarcraftlogsId(Long warcraftlogsId) {
        this.warcraftlogsId = warcraftlogsId;
        return this;
    }
}
