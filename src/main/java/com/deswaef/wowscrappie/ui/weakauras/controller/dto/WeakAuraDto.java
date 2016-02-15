package com.deswaef.wowscrappie.ui.weakauras.controller.dto;


import com.deswaef.wowscrappie.expansion.domain.Patch;
import com.deswaef.wowscrappie.ui.weakauras.domain.WeakAura;

public class WeakAuraDto {
    private Long id;
    private String name;
    private String actualValue;
    private String comment;
    private String[] imageRefs;
    private String uploader;
    private long rating = 0;
    private boolean approved;
    private long uploaderId;
    private Patch patch;

    public static WeakAuraDto fromWeakAura(WeakAura weakAura) {
        return new WeakAuraDto()
                .setId(weakAura.getId())
                .setName(weakAura.getName())
                .setComment(weakAura.getComment())
                .setActualValue(weakAura.getActualValue())
                .setUploader(weakAura.getUploader().getUsername())
                .setApproved(weakAura.isApproved())
                .setUploaderId(weakAura.getUploader().getId());
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

    public long getRating() {
        return rating;
    }

    public WeakAuraDto setRating(long rating) {
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

    public long getUploaderId() {
        return uploaderId;
    }

    public WeakAuraDto setUploaderId(long uploaderId) {
        this.uploaderId = uploaderId;
        return this;
    }

    public Patch getPatch() {
        return patch;
    }

    public WeakAuraDto setPatch(Patch patch) {
        this.patch = patch;
        return this;
    }
}
