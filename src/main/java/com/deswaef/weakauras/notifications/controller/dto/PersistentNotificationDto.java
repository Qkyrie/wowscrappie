package com.deswaef.weakauras.notifications.controller.dto;

public class PersistentNotificationDto {
    private String content;
    private String url;
    private String title;

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
}
