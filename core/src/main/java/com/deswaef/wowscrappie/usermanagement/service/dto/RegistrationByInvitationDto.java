package com.deswaef.wowscrappie.usermanagement.service.dto;

public class RegistrationByInvitationDto {
    private String newName;
    private String password;
    private String password_repeat;
    private String email;
    private boolean hasErrors;
    private String errorMessage;
    private String invitationcode;


    public String getNewName() {
        return newName;
    }

    public RegistrationByInvitationDto setNewName(String newName) {
        this.newName = newName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegistrationByInvitationDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPassword_repeat() {
        return password_repeat;
    }

    public RegistrationByInvitationDto setPassword_repeat(String password_repeat) {
        this.password_repeat = password_repeat;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegistrationByInvitationDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public RegistrationByInvitationDto setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public RegistrationByInvitationDto setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public String getInvitationcode() {
        return invitationcode;
    }

    public RegistrationByInvitationDto setInvitationcode(String invitationcode) {
        this.invitationcode = invitationcode;
        return this;
    }
}
