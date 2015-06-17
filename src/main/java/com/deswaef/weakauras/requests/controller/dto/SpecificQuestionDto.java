package com.deswaef.weakauras.requests.controller.dto;

import com.deswaef.weakauras.requests.domain.ConfigRequest;
import org.ocpsoft.prettytime.PrettyTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * User: Quinten
 * Date: 2-6-2015
 * Time: 00:06
 *
 * @author Quinten De Swaef
 */
public class SpecificQuestionDto {

    private static final PrettyTime prettyTime = new PrettyTime(Locale.ENGLISH);


    private Long id;
    private String title;
    private String content;

    private String posterName;
    private Long posterId;

    private String lastEditDate;
    private String postDate;

    private List<QuestionResponseDto> responses = new ArrayList<>();

    public static SpecificQuestionDto fromConfigRequest(ConfigRequest configRequest) {
        return new SpecificQuestionDto()
                .setContent(configRequest.getQuestion())
                .setTitle(configRequest.getTitle())
                .setId(configRequest.getId())
                .setPosterId(configRequest.getPoster().getId())
                .setPosterName(configRequest.getPoster().getUsername())
                .setLastEditDate(prettyTime.format(configRequest.getLastEditDate()))
                .setPostDate(prettyTime.format(configRequest.getOriginalPostDate()));
    }

    public Long getId() {
        return id;
    }

    public SpecificQuestionDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SpecificQuestionDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SpecificQuestionDto setContent(String content) {
        this.content = content;
        return this;
    }

    public String getPosterName() {
        return posterName;
    }

    public SpecificQuestionDto setPosterName(String posterName) {
        this.posterName = posterName;
        return this;
    }

    public Long getPosterId() {
        return posterId;
    }

    public SpecificQuestionDto setPosterId(Long posterId) {
        this.posterId = posterId;
        return this;
    }

    public String getLastEditDate() {
        return lastEditDate;
    }

    public SpecificQuestionDto setLastEditDate(String lastEditDate) {
        this.lastEditDate = lastEditDate;
        return this;
    }

    public String getPostDate() {
        return postDate;
    }

    public SpecificQuestionDto setPostDate(String postDate) {
        this.postDate = postDate;
        return this;
    }

    public List<QuestionResponseDto> getResponses() {
        return responses;
    }

    public SpecificQuestionDto setResponses(List<QuestionResponseDto> responses) {
        this.responses = responses;
        return this;
    }
}
