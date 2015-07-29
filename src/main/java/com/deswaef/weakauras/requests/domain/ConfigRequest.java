package com.deswaef.weakauras.requests.domain;

import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * User: Quinten
 * Date: 27-5-2015
 * Time: 21:06
 *
 * @author Quinten De Swaef
 */
@Entity
@Table(name = "configrequest")
public class ConfigRequest {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "question")
    @Lob
    private String question;

    @Column(name = "original_postdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date originalPostDate;

    @Column(name = "last_edit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastEditDate;

    @Column(name = "deleted")
    private boolean deleted = false;

    @Column(name = "viewcount")
    private Long views = 0L;

    @ManyToOne
    @JoinColumn(name = "poster_id")
    private ScrappieUser poster;

    public Long getId() {
        return id;
    }

    public ConfigRequest setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ConfigRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getQuestion() {
        return question;
    }

    public ConfigRequest setQuestion(String question) {
        this.question = question;
        return this;
    }

    public Date getOriginalPostDate() {
        return originalPostDate;
    }

    public ConfigRequest setOriginalPostDate(Date originalPostDate) {
        this.originalPostDate = originalPostDate;
        return this;
    }

    public Date getLastEditDate() {
        return lastEditDate;
    }

    public ConfigRequest setLastEditDate(Date lastEditDate) {
        this.lastEditDate = lastEditDate;
        return this;
    }

    public ScrappieUser getPoster() {
        return poster;
    }

    public ConfigRequest setPoster(ScrappieUser poster) {
        this.poster = poster;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public ConfigRequest setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public Long getViews() {
        return views;
    }

    public ConfigRequest setViews(Long views) {
        this.views = views;
        return this;
    }
}
