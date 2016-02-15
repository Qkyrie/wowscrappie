package com.deswaef.wowscrappie.expansion.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "expansion")
public class Expansion implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "start_date")
    private Date startDate;

    public Long getId() {
        return id;
    }

    public Expansion setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Expansion setName(String name) {
        this.name = name;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public Expansion setSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Expansion setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

}
