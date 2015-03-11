package com.deswaef.weakauras.ui.weakauras.domain;

import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "weak_aura")
@DiscriminatorColumn(name="wa_type")
public class WeakAura {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    @Size(max = 100)
    private String name;

    @Lob
    @Column(name = "comment")
    private String comment;

    @Lob
    @Column(name= "actual_value")
    private String actualValue;

    @Column(name = "approved")
    private boolean approved = false;

    @ManyToOne
    @JoinColumn(name = "uploader_id")
    private ScrappieUser uploader;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActualValue() {
        return actualValue;
    }

    public WeakAura setActualValue(String actualValue) {
        this.actualValue = actualValue;
        return this;
    }

    public String getName() {
        return name;
    }

    public WeakAura setName(String name) {
        this.name = name;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public WeakAura setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public boolean isApproved() {
        return approved;
    }

    public WeakAura setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public ScrappieUser getUploader() {
        return uploader;
    }

    public WeakAura setUploader(ScrappieUser uploader) {
        this.uploader = uploader;
        return this;
    }
}
