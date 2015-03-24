package com.deswaef.weakauras.messaging.controller.dto;

import com.deswaef.weakauras.messaging.domain.PrivateMessage;

public class PrivateMessageListDto {
    private Long id;
    private String when;
    private String from;
    private Long fromId;
    private String title;

    public static PrivateMessageListDto create(PrivateMessage privateMessage) {
        return new PrivateMessageListDto()
                .setFrom(privateMessage.getFromUser().getUsername())
                .setId(privateMessage.getId())
                .setFromId(privateMessage.getFromUser().getId())
                .setTitle(privateMessage.getTitle())
                .setWhen(privateMessage.getDateOfPosting().toString());
    }

    public Long getId() {
        return id;
    }

    public PrivateMessageListDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getWhen() {
        return when;
    }

    public PrivateMessageListDto setWhen(String when) {
        this.when = when;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public PrivateMessageListDto setFrom(String from) {
        this.from = from;
        return this;
    }

    public Long getFromId() {
        return fromId;
    }

    public PrivateMessageListDto setFromId(Long fromId) {
        this.fromId = fromId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PrivateMessageListDto setTitle(String title) {
        this.title = title;
        return this;
    }
}