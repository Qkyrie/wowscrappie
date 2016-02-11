package com.deswaef.wowscrappie.messaging.controller.dto;

import com.deswaef.wowscrappie.messaging.domain.PrivateMessage;
import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.Locale;

public class PrivateMessageListDto {

    private static final PrettyTime prettyTime = new PrettyTime(Locale.ENGLISH);

    private Long id;
    private String when;
    private String from;
    private Long fromId;
    private String title;
    private Date actualDate;

    public static PrivateMessageListDto create(PrivateMessage privateMessage) {
        return new PrivateMessageListDto()
                .setFrom(privateMessage.getFromUser().getUsername())
                .setId(privateMessage.getId())
                .setFromId(privateMessage.getFromUser().getId())
                .setTitle(privateMessage.getTitle())
                .setWhen(prettyTime.format(privateMessage.getDateOfPosting()))
                .setActualDate(privateMessage.getDateOfPosting());
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

    public Date getActualDate() {
        return actualDate;
    }

    public PrivateMessageListDto setActualDate(Date actualDate) {
        this.actualDate = actualDate;
        return this;
    }
}
