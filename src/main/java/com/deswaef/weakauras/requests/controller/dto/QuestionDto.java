package com.deswaef.weakauras.requests.controller.dto;

import com.deswaef.weakauras.requests.domain.ConfigRequest;
import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.Locale;

public class QuestionDto {
    private static final PrettyTime prettyTime = new PrettyTime(Locale.ENGLISH);

    private Long id;
    private String title;
    private String poster;
    private Long posterId;
    private Date originalPostDate;
    private String originalPostDatePretty;
    private String content;

    public static QuestionDto fromConfigRequest(ConfigRequest configRequest) {
        return new QuestionDto()
                .setId(configRequest.getId())
                .setOriginalPostDate(configRequest.getOriginalPostDate())
                .setOriginalPostDatePretty(prettyTime.format(configRequest.getOriginalPostDate()))
                .setPoster(configRequest.getPoster().getUsername())
                .setPosterId(configRequest.getPoster().getId())
                .setTitle(configRequest.getTitle())
                .setContent(configRequest.getQuestion());
    }

    public Long getId() {
        return id;
    }

    public QuestionDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public QuestionDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPoster() {
        return poster;
    }

    public QuestionDto setPoster(String poster) {
        this.poster = poster;
        return this;
    }

    public Long getPosterId() {
        return posterId;
    }

    public QuestionDto setPosterId(Long posterId) {
        this.posterId = posterId;
        return this;
    }

    public Date getOriginalPostDate() {
        return originalPostDate;
    }

    public QuestionDto setOriginalPostDate(Date originalPostDate) {
        this.originalPostDate = originalPostDate;
        return this;
    }

    public static PrettyTime getPrettyTime() {
        return prettyTime;
    }

    public String getOriginalPostDatePretty() {
        return originalPostDatePretty;
    }

    public QuestionDto setOriginalPostDatePretty(String originalPostDatePretty) {
        this.originalPostDatePretty = originalPostDatePretty;
        return this;
    }

    public String getContent() {
        return content;
    }

    public QuestionDto setContent(String content) {
        this.content = content;
        return this;
    }
}
