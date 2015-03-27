package com.deswaef.weakauras.ui.tellmewhen.domain;

import com.deswaef.weakauras.ui.comments.domain.TellMeWhenComment;
import com.deswaef.weakauras.ui.comments.domain.WeakAuraComment;
import com.deswaef.weakauras.ui.reports.domain.TellMeWhenReport;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "tell_me_when")
@DiscriminatorColumn(name="tmw_type")
public class TellMeWhen {

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

    @OneToMany(mappedBy = "tellMeWhen", orphanRemoval = true, cascade = {CascadeType.REMOVE})
    private Set<TellMeWhenComment> comments;

    @OneToMany(mappedBy = "tellMeWhen", orphanRemoval = true, cascade = {CascadeType.REMOVE})
    private Set<TellMeWhenReport> reports;

    public Long getId() {
        return id;
    }

    public TellMeWhen setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TellMeWhen setName(String name) {
        this.name = name;
        return this;
    }

    public String getActualValue() {
        return actualValue;
    }

    public TellMeWhen setActualValue(String actualValue) {
        this.actualValue = actualValue;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public TellMeWhen setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public boolean isApproved() {
        return approved;
    }

    public TellMeWhen setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public ScrappieUser getUploader() {
        return uploader;
    }

    public TellMeWhen setUploader(ScrappieUser scrappieUser) {
        this.uploader = scrappieUser;
        return this;
    }

    public Set<TellMeWhenComment> getComments() {
        return comments;
    }

    public TellMeWhen setComments(Set<TellMeWhenComment> comments) {
        this.comments = comments;
        return this;
    }

    public Set<TellMeWhenReport> getReports() {
        return reports;
    }

    public TellMeWhen setReports(Set<TellMeWhenReport> reports) {
        this.reports = reports;
        return this;
    }
}
