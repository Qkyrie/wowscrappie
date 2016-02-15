package com.deswaef.wowscrappie.usermanagement.service.dto;

public class CreateInvitationDto {
    private String email;
    private boolean hasError;
    private String errorMessage;
    private String invitationcode;

    public String getEmail() {
        return email;
    }

    public CreateInvitationDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public boolean isHasError() {
        return hasError;
    }

    public CreateInvitationDto setHasError(boolean hasError) {
        this.hasError = hasError;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public CreateInvitationDto setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public String getInvitationcode() {
        return invitationcode;
    }

    public CreateInvitationDto setInvitationcode(String invitationcode) {
        this.invitationcode = invitationcode;
        return this;
    }
}
