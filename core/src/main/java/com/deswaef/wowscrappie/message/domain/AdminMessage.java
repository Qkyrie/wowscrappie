package com.deswaef.wowscrappie.message.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "adminmessage")
public class AdminMessage {

    @Id
    @GeneratedValue
    private Long id;
    private String message;
    @Column(name = "dateOfPosting")
    private Date dateOfPosting;

    public Long getId() {
        return id;
    }

    public AdminMessage setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AdminMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public Date getDateOfPosting() {
        return dateOfPosting;
    }

    public AdminMessage setDateOfPosting(Date dateOfPosting) {
        this.dateOfPosting = dateOfPosting;
        return this;
    }
}
