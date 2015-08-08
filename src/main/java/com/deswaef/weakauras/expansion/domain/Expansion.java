package com.deswaef.weakauras.expansion.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "expansion")
public class Expansion {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "name")
    private String slug;

    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;

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

    public Date getEndDate() {
        return endDate;
    }

    public Expansion setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Expansion setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }
}
