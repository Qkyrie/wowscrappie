package com.deswaef.weakauras.mvc.dto;

public class ContactRequestDto {
    private String title;
    private String content;
    private Long toUserId;

    private boolean hasErrors = false;
    private String errorMessage;

    public String getTitle() {
        return title;
    }

    public ContactRequestDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ContactRequestDto setContent(String content) {
        this.content = content;
        return this;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public ContactRequestDto setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ContactRequestDto setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public ContactRequestDto setToUserId(Long toUserId) {
        this.toUserId = toUserId;
        return this;
    }
}
