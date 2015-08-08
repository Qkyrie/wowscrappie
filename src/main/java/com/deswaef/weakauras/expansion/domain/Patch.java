package com.deswaef.weakauras.expansion.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "patch")
public class Patch {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "main_features")
    private String mainFeatures;

    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;

    public Long getId() {
        return id;
    }

    public Patch setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Patch setName(String name) {
        this.name = name;
        return this;
    }

    public String getMainFeatures() {
        return mainFeatures;
    }

    public Patch setMainFeatures(String mainFeatures) {
        this.mainFeatures = mainFeatures;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Patch setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Patch setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }
}
