package com.deswaef.weakauras.ui.mvc.dto;

public class EditConfigurationDto {
    private Long id;
    private String type;
    private String caption;
    private String actualValue;
    private String comments;
    private String uploader;
    private long rating;

    private boolean hasErrors;
    private String errorMessage;

    public static EditConfigurationDto create() {
        return new EditConfigurationDto();
    }

    public Long getId() {
        return id;
    }

    public EditConfigurationDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public EditConfigurationDto setType(String type) {
        this.type = type;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public EditConfigurationDto setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public String getActualValue() {
        return actualValue;
    }

    public EditConfigurationDto setActualValue(String actualValue) {
        this.actualValue = actualValue;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public EditConfigurationDto setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public String getUploader() {
        return uploader;
    }

    public EditConfigurationDto setUploader(String uploader) {
        this.uploader = uploader;
        return this;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public EditConfigurationDto setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public EditConfigurationDto setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public long getRating() {
        return rating;
    }

    public EditConfigurationDto setRating(long rating) {
        this.rating = rating;
        return this;
    }
}
