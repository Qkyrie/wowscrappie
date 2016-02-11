package com.deswaef.wowscrappie.messaging.controller.dto;

public class PrivateMessageReplyDto {
    private Long originalMessageId;
    private String content;
    private boolean hasErrors;
    private String errorMessage;

    public String getContent() {
        return content;
    }

    public PrivateMessageReplyDto setContent(String content) {
        this.content = content;
        return this;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public PrivateMessageReplyDto setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public PrivateMessageReplyDto setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public Long getOriginalMessageId() {
        return originalMessageId;
    }

    public PrivateMessageReplyDto setOriginalMessageId(Long originalMessageId) {
        this.originalMessageId = originalMessageId;
        return this;
    }
}
