package com.deswaef.wowscrappie.administration.controller.dto;

import com.deswaef.wowscrappie.ui.weakauras.domain.BossFightWeakAura;
import com.deswaef.wowscrappie.ui.weakauras.domain.SpecWeakAura;
import com.deswaef.wowscrappie.ui.weakauras.domain.WeakAura;
import com.deswaef.wowscrappie.ui.weakauras.domain.WowClassWeakAura;

public class PendingWeakAuraDto {
    private Long id;
    private String name;
    private String comment;
    private String actualValue;
    private boolean approved;
    private String type;
    private String whatFor;
    private String uploader;
    private long uploaderId;

    public static PendingWeakAuraDto create(WeakAura weakAura) {
        return new PendingWeakAuraDto()
                .setId(weakAura.getId())
                .setActualValue(weakAura.getActualValue())
                .setApproved(weakAura.isApproved())
                .setComment(weakAura.getComment())
                .setName(weakAura.getName())
                .setType(extractType(weakAura))
                .setWhatFor(extractWhatFor(weakAura))
                .setUploader(weakAura.getUploader().getUsername())
                .setUploaderId(weakAura.getUploader().getId());
    }

    private static String extractWhatFor(WeakAura weakAura) {
        if (weakAura instanceof SpecWeakAura) {
            return String.format("%s %s", ((SpecWeakAura) weakAura).getSpec().getName(), ((SpecWeakAura) weakAura).getSpec().getWowClass().getName());
        } else if (weakAura instanceof WowClassWeakAura) {
            return ((WowClassWeakAura) weakAura).getWowClass().getName();
        } else if (weakAura instanceof BossFightWeakAura) {
            return ((BossFightWeakAura) weakAura).getBoss().getName();
        } else {
            return "unknown";
        }
    }

    private static String extractType(WeakAura weakAura) {
        if (weakAura instanceof SpecWeakAura) {
            return "spec";
        } else if (weakAura instanceof WowClassWeakAura) {
            return "class";
        } else if (weakAura instanceof BossFightWeakAura) {
            return "bossfight";
        } else {
            return "unknown";
        }
    }

    public Long getId() {
        return id;
    }

    public PendingWeakAuraDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PendingWeakAuraDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public PendingWeakAuraDto setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getActualValue() {
        return actualValue;
    }

    public PendingWeakAuraDto setActualValue(String actualValue) {
        this.actualValue = actualValue;
        return this;
    }

    public boolean isApproved() {
        return approved;
    }

    public PendingWeakAuraDto setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public String getType() {
        return type;
    }

    public PendingWeakAuraDto setType(String type) {
        this.type = type;
        return this;
    }

    public String getWhatFor() {
        return whatFor;
    }

    public PendingWeakAuraDto setWhatFor(String whatFor) {
        this.whatFor = whatFor;
        return this;
    }

    public String getUploader() {
        return uploader;
    }

    public PendingWeakAuraDto setUploader(String uploader) {
        this.uploader = uploader;
        return this;
    }

    public long getUploaderId() {
        return uploaderId;
    }

    public PendingWeakAuraDto setUploaderId(long uploaderId) {
        this.uploaderId = uploaderId;
        return this;
    }
}
