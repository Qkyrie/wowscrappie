package com.deswaef.weakauras.notifications.controller.dto;

import java.util.Date;

public class PersistentNotificationDto {
    private Long id;
    private String content;
    private String url;
    private String title;
    private String postDate;

    public static PersistentNotificationDto create() {
        return new PersistentNotificationDto();
    }

    public String getContent() {
        return content;
    }

    public PersistentNotificationDto setContent(String content) {
        this.content = content;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PersistentNotificationDto setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PersistentNotificationDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public Long getId() {
        return id;
    }

    public PersistentNotificationDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPostDate() {
        return postDate;
    }

    public PersistentNotificationDto setPostDate(String postDate) {
        this.postDate = postDate;
        return this;
    }
}
