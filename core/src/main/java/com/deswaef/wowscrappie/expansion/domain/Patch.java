package com.deswaef.wowscrappie.expansion.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "patch")
public class Patch implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "main_features")
    private String mainFeatures;

    @Column(name = "start_date")
    private Date startDate;

    @JoinColumn(name = "expansion_id")
    @ManyToOne
    private Expansion expansion;

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

    public Expansion getExpansion() {
        return expansion;
    }

    public Patch setExpansion(Expansion expansion) {
        this.expansion = expansion;
        return this;
    }
}
