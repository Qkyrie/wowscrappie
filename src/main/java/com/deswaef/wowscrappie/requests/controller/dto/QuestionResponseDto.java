package com.deswaef.wowscrappie.requests.controller.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionResponseDto {
    private Long id;
    private String response;
    private String poster;
    private Long posterId;
    private String prettyDate;
    private Date date;
    private boolean isOwn;
    private boolean deleted = false;

    private List<QuestionResponseDto> responses = new ArrayList<>();

    public String getResponse() {
        return response;
    }

    public QuestionResponseDto setResponse(String response) {
        this.response = response;
        return this;
    }

    public String getPoster() {
        return poster;
    }

    public QuestionResponseDto setPoster(String poster) {
        this.poster = poster;
        return this;
    }

    public String getPrettyDate() {
        return prettyDate;
    }

    public QuestionResponseDto setPrettyDate(String prettyDate) {
        this.prettyDate = prettyDate;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public QuestionResponseDto setDate(Date date) {
        this.date = date;
        return this;
    }

    public Long getPosterId() {
        return posterId;
    }

    public QuestionResponseDto setPosterId(Long posterId) {
        this.posterId = posterId;
        return this;
    }

    public List<QuestionResponseDto> getResponses() {
        return responses;
    }

    public QuestionResponseDto setResponses(List<QuestionResponseDto> responses) {
        this.responses = responses;
        return this;
    }

    public Long getId() {
        return id;
    }

    public QuestionResponseDto setId(Long id) {
        this.id = id;
        return this;
    }

    public boolean isOwn() {
        return isOwn;
    }

    public QuestionResponseDto setOwn(boolean isOwn) {
        this.isOwn = isOwn;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public QuestionResponseDto setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }
}
