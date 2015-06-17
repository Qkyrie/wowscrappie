package com.deswaef.weakauras.requests.controller.dto;

public class CreateResponseDto {
    private String userComment;
    private Long questionId;
    private boolean hasErrors;
    private String errorMessage;

    public String getUserComment() {
        return userComment;
    }

    public CreateResponseDto setUserComment(String userComment) {
        this.userComment = userComment;
        return this;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public CreateResponseDto setQuestionId(Long questionId) {
        this.questionId = questionId;
        return this;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public CreateResponseDto setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public CreateResponseDto setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
