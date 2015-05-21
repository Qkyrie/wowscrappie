package com.deswaef.weakauras.ui.macros.controller.dto;

import com.deswaef.weakauras.ui.macros.domain.Macro;

public class MacroDto {
    private Long id;
    private String name;
    private String actualValue;
    private String comment;
    private String uploader;
    private long rating = 0;
    private boolean approved;
    private long uploaderId;

    public static MacroDto fromMacro(Macro macro) {
        return new MacroDto()
                .setId(macro.getId())
                .setComment(macro.getDescription())
                .setName(macro.getName())
                .setActualValue(macro.getActualValue())
                .setUploader(macro.getUploader().getUsername())
                .setApproved(macro.isApproved())
                .setUploaderId(macro.getUploader().getId());
    }

    public Long getId() {
        return id;
    }

    public MacroDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MacroDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getActualValue() {
        return actualValue;
    }

    public MacroDto setActualValue(String actualValue) {
        this.actualValue = actualValue;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public MacroDto setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getUploader() {
        return uploader;
    }

    public MacroDto setUploader(String uploader) {
        this.uploader = uploader;
        return this;
    }

    public long getRating() {
        return rating;
    }

    public MacroDto setRating(long rating) {
        this.rating = rating;
        return this;
    }

    public boolean isApproved() {
        return approved;
    }

    public MacroDto setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public long getUploaderId() {
        return uploaderId;
    }

    public MacroDto setUploaderId(long uploaderId) {
        this.uploaderId = uploaderId;
        return this;
    }
}
