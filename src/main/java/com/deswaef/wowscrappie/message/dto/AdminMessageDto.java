package com.deswaef.wowscrappie.message.dto;

import java.util.Date;

public class AdminMessageDto {
    private Long id;
    private String message;
    private Date dateOfPosting;

    private AdminMessageDto() {}

    public static AdminMessageDto createAdminMessage() {
        return new AdminMessageDto();
    }

    public AdminMessageDto setId(Long id) {
        this.id = id;
        return this;
    }

    public AdminMessageDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public AdminMessageDto setDateOfPosting(Date dateOfPosting) {
        this.dateOfPosting = dateOfPosting;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getDateOfPosting() {
        return dateOfPosting;
    }
}
