package com.deswaef.weakauras.notifications.domain;

import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;

import javax.persistence.*;

@Entity
@Table(name = "persistent_notification")
public class PersistentNotification {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name= "url")
    private String url;
    @Lob
    @Column(name = "content")
    private String content;
    @JoinColumn(name = "for_user")
    @ManyToOne
    private ScrappieUser forUser;
    @Column(name = "readStatus")
    private boolean readStatus = false;
    public Long getId() {
        return id;
    }

    public PersistentNotification setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PersistentNotification setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PersistentNotification setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PersistentNotification setContent(String content) {
        this.content = content;
        return this;
    }

    public ScrappieUser getForUser() {
        return forUser;
    }

    public PersistentNotification setForUser(ScrappieUser forUser) {
        this.forUser = forUser;
        return this;
    }

    public boolean isReadStatus() {
        return readStatus;
    }

    public PersistentNotification setReadStatus(boolean read) {
        this.readStatus = read;
        return this;
    }
}
