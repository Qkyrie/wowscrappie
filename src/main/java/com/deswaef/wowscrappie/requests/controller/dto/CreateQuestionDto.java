package com.deswaef.wowscrappie.requests.controller.dto;

import com.deswaef.wowscrappie.requests.domain.ConfigRequest;

public class CreateQuestionDto {

    private String title;
    private String question;
    private Long id;
    private boolean hasErrors = false;
    private String errorMessage;

    public static CreateQuestionDto fromConfigRequest(ConfigRequest configRequest) {
        return new CreateQuestionDto()
                .setId(configRequest.getId())
                .setQuestion(configRequest.getQuestion())
                .setTitle(configRequest.getTitle());
    }

    public String getTitle() {
        return title;
    }

    public CreateQuestionDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getQuestion() {
        return question;
    }

    public CreateQuestionDto setQuestion(String question) {
        this.question = question;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public CreateQuestionDto setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public CreateQuestionDto setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
        return this;
    }

    public Long getId() {
        return id;
    }

    public CreateQuestionDto setId(Long id) {
        this.id = id;
        return this;
    }
}
