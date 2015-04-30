package com.deswaef.weakauras.realm.domain;

import javax.persistence.*;

@Entity
@Table(name = "realm")
public class Realm {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String slug;
    @Enumerated(EnumType.STRING)
    private Locality locality;

    public Long getId() {
        return id;
    }

    public Realm setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Realm setName(String name) {
        this.name = name;
        return this;
    }

    public Locality getLocality() {
        return locality;
    }

    public Realm setLocality(Locality locality) {
        this.locality = locality;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public Realm setSlug(String slug) {
        this.slug = slug;
        return this;
    }
}
