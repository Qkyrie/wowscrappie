package com.deswaef.wowscrappie.applicationevent.controller;

import com.deswaef.wowscrappie.applicationevent.ApplicationEvent;
import com.deswaef.wowscrappie.applicationevent.ApplicationEventTypeEnum;
import org.ocpsoft.prettytime.PrettyTime;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Locale;

public class ApplicationEventDto {

    private static PrettyTime prettyTime = new PrettyTime(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), Locale.ENGLISH);
    private static DateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    private long id;
    private ApplicationEventTypeEnum eventType;
    private String eventTimePretty;
    private String eventTime;
    private String message;


    public static ApplicationEventDto from(ApplicationEvent event) {
        return new ApplicationEventDto()
                .setEventTime(format.format(event.getEventTime()))
                .setEventTimePretty(prettyTime.format(event.getEventTime()))
                .setId(event.getId())
                .setEventType(event.getEventType())
                .setMessage(event.getMessage());
    }

    public String getMessage() {
        return message;
    }

    public ApplicationEventDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getEventTime() {
        return eventTime;
    }

    public ApplicationEventDto setEventTime(String eventTime) {
        this.eventTime = eventTime;
        return this;
    }

    public String getEventTimePretty() {
        return eventTimePretty;
    }

    public ApplicationEventDto setEventTimePretty(String eventTimePretty) {
        this.eventTimePretty = eventTimePretty;
        return this;
    }

    public ApplicationEventTypeEnum getEventType() {
        return eventType;
    }

    public ApplicationEventDto setEventType(ApplicationEventTypeEnum eventType) {
        this.eventType = eventType;
        return this;
    }

    public long getId() {
        return id;
    }

    public ApplicationEventDto setId(long id) {
        this.id = id;
        return this;
    }
}
