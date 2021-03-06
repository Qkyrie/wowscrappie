package com.deswaef.wowscrappie.ui.weakauras.domain;

import com.deswaef.wowscrappie.ui.comments.domain.WeakAuraComment;
import com.deswaef.wowscrappie.ui.reports.domain.WeakAuraReport;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

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

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @ManyToOne
    @JoinColumn(name = "uploader_id")
    private ScrappieUser uploader;

    @OneToMany(mappedBy = "weakAura", orphanRemoval = true, cascade = {CascadeType.REMOVE})
    private Set<WeakAuraComment> comments;

    @OneToMany(mappedBy = "weakAura", orphanRemoval = true, cascade = {CascadeType.REMOVE})
    private Set<WeakAuraReport> reports;

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

    public Set<WeakAuraComment> getComments() {
        return comments;
    }

    public WeakAura setComments(Set<WeakAuraComment> comments) {
        this.comments = comments;
        return this;
    }

    public Set<WeakAuraReport> getReports() {
        return reports;
    }

    public WeakAura setReports(Set<WeakAuraReport> reports) {
        this.reports = reports;
        return this;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public WeakAura setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
        return this;
    }
}
