package com.deswaef.weakauras.ui.weakauras.controller.dto;


import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;

public class WeakAuraDto {
    private Long id;
    private String name;
    private String actualValue;
    private String comment;
    private String[] imageRefs;
    private String uploader;
    private String rating = "0%";
    private boolean approved;

    public static WeakAuraDto fromWeakAura(WeakAura weakAura) {
        return new WeakAuraDto()
                .setId(weakAura.getId())
                .setName(weakAura.getName())
                .setComment(weakAura.getComment())
                .setActualValue(weakAura.getActualValue())
                .setUploader(weakAura.getUploader().getUsername())
                .setApproved(weakAura.isApproved());
    }

    public Long getId() {
        return id;
    }

    public WeakAuraDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public WeakAuraDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getActualValue() {
        return actualValue;
    }

    public WeakAuraDto setActualValue(String actualValue) {
        this.actualValue = actualValue;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public WeakAuraDto setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String[] getImageRefs() {
        return imageRefs;
    }

    public WeakAuraDto setImageRefs(String[] imageRefs) {
        this.imageRefs = imageRefs;
        return this;
    }

    public String getUploader() {
        return uploader;
    }

    public WeakAuraDto setUploader(String uploader) {
        this.uploader = uploader;
        return this;
    }

    public String getRating() {
        return rating;
    }

    public WeakAuraDto setRating(String rating) {
        this.rating = rating;
        return this;
    }

    public boolean isApproved() {
        return approved;
    }

    public WeakAuraDto setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }
}
