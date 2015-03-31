package com.deswaef.weakauras.ui.tellmewhen.controller.dto;

import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;

public class TellMeWhenDto {
    private Long id;
    private String name;
    private String actualValue;
    private String comment;
    private String[] imageRefs;
    private String uploader;
    private String rating = "0%";
    private boolean approved;
    private long uploaderId;

    public static TellMeWhenDto fromTellMeWhen(TellMeWhen tmw) {
        return new TellMeWhenDto()
                .setComment(tmw.getComment())
                .setName(tmw.getName())
                .setId(tmw.getId())
                .setActualValue(tmw.getActualValue())
                .setUploader(tmw.getUploader().getUsername())
                .setApproved(tmw.isApproved())
                .setUploaderId(tmw.getUploader().getId());
    }

    public Long getId() {
        return id;
    }

    public TellMeWhenDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TellMeWhenDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getActualValue() {
        return actualValue;
    }

    public TellMeWhenDto setActualValue(String actualValue) {
        this.actualValue = actualValue;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public TellMeWhenDto setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String[] getImageRefs() {
        return imageRefs;
    }

    public TellMeWhenDto setImageRefs(String[] imageRefs) {
        this.imageRefs = imageRefs;
        return this;
    }

    public String getUploader() {
        return uploader;
    }

    public TellMeWhenDto setUploader(String uploader) {
        this.uploader = uploader;
        return this;
    }

    public String getRating() {
        return rating;
    }

    public TellMeWhenDto setRating(String rating) {
        this.rating = rating;
        return this;
    }

    public boolean isApproved() {
        return approved;
    }

    public TellMeWhenDto setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public long getUploaderId() {
        return uploaderId;
    }

    public TellMeWhenDto setUploaderId(long uploaderId) {
        this.uploaderId = uploaderId;
        return this;
    }
}
