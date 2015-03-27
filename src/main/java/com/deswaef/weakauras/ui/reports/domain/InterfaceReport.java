package com.deswaef.weakauras.ui.reports.domain;

import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "interface_report")
@DiscriminatorColumn(name="interface_type")
public class InterfaceReport {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "post_date")
    private Date postDate;

    @Column(name = "handled")
    private boolean handled = false;

    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private ScrappieUser reporter;

    public Long getId() {
        return id;
    }

    public InterfaceReport setId(Long id) {
        this.id = id;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public InterfaceReport setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public ScrappieUser getReporter() {
        return reporter;
    }

    public InterfaceReport setReporter(ScrappieUser commenter) {
        this.reporter = commenter;
        return this;
    }

    public Date getPostDate() {
        return postDate;
    }

    public InterfaceReport setPostDate(Date postDate) {
        this.postDate = postDate;
        return this;
    }

    public boolean isHandled() {
        return handled;
    }

    public InterfaceReport setHandled(boolean handled) {
        this.handled = handled;
        return this;
    }
}
