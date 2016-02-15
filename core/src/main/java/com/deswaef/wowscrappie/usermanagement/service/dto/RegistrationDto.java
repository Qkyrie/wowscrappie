package com.deswaef.wowscrappie.usermanagement.service.dto;

public class RegistrationDto {
    private String email;

    private boolean hasErrors;
    private String errorMessage;

    public static RegistrationDto create() {
        return new RegistrationDto();
    }

    public String getEmail() {
        return email;
    }

    public RegistrationDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public RegistrationDto setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public RegistrationDto setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
