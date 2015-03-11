package com.deswaef.weakauras.ui.macros.domain;

import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "macro")
@DiscriminatorColumn(name="macro_type")
public class Macro {

    @Id
    @GeneratedValue
    private Long id;

    @Size(max = 100)
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "approved")
    private boolean approved = false;

    @Lob
    @Column(name = "actual_value")
    private String actualValue;

    @ManyToOne
    @JoinColumn(name = "uploader_id")
    private ScrappieUser uploader;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Macro setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Macro setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getActualValue() {
        return actualValue;
    }

    public Macro setActualValue(String actualValue) {
        this.actualValue = actualValue;
        return this;
    }

    public boolean isApproved() {
        return approved;
    }

    public Macro setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public String getComment() {
        return description;
    }

    public ScrappieUser getUploader() {
        return uploader;
    }

    public Macro setUploader(ScrappieUser uploader) {
        this.uploader = uploader;
        return this;
    }
}
