package com.deswaef.weakauras.messaging.controller.dto;

public class PrivateMessageDetailsDto {
    private String when;
    private String title;
    private String content;
    private boolean inward;
    private String sender;

    public String getWhen() {
        return when;
    }

    public PrivateMessageDetailsDto setWhen(String when) {
        this.when = when;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PrivateMessageDetailsDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PrivateMessageDetailsDto setContent(String content) {
        this.content = content;
        return this;
    }

    public boolean isInward() {
        return inward;
    }

    public PrivateMessageDetailsDto setInward(boolean inward) {
        this.inward = inward;
        return this;
    }

    public String getSender() {
        return sender;
    }

    public PrivateMessageDetailsDto setSender(String sender) {
        this.sender = sender;
        return this;
    }
}
