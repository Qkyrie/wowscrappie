package com.deswaef.wowscrappie.applicationevent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "application_events")
public class ApplicationEvent {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "event_type")
    private ApplicationEventTypeEnum eventType;

    @Column(name = "event_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventTime;

    @Column(name = "event_message")
    private String message;

    public Long getId() {
        return id;
    }

    public ApplicationEvent setId(Long id) {
        this.id = id;
        return this;
    }

    public ApplicationEventTypeEnum getEventType() {
        return eventType;
    }

    public ApplicationEvent setEventType(ApplicationEventTypeEnum eventType) {
        this.eventType = eventType;
        return this;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public ApplicationEvent setEventTime(Date eventTime) {
        this.eventTime = eventTime;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApplicationEvent setMessage(String message) {
        this.message = message;
        return this;
    }
}
