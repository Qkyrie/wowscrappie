package com.deswaef.wowscrappie.notifications.domain;

import java.util.Date;

public class Notification {
    private Date postDate;
    private String message;
    private String url;

    public static Notification create(String message) {
        return new Notification()
                .setPostDate(now())
                .setMessage(message);
    }

    private static Date now() {
        return new Date();
    }

    public Date getPostDate() {
        return postDate;
    }

    public Notification setPostDate(Date postDate) {
        this.postDate = postDate;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Notification setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Notification setUrl(String url) {
        this.url = url;
        return this;
    }
}
