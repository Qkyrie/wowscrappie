package com.deswaef.weakauras.usermanagement.controller.dto;

public class RequestInvitationDto {
    private String email;
    private String reason;

    private boolean hasErrors;
    private String errorMessage;

    public static RequestInvitationDto create() {
        return new RequestInvitationDto();
    }

    public String getEmail() {
        return email;
    }

    public RequestInvitationDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public RequestInvitationDto setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public RequestInvitationDto setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public RequestInvitationDto setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
