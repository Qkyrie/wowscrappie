package com.deswaef.weakauras.administration.controller.dto;

import com.deswaef.weakauras.ui.tellmewhen.domain.SpecTellMeWhen;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.weakauras.ui.tellmewhen.domain.WowClassTellMeWhen;

public class PendingTellMeWhenDto {

    private Long id;
    private String name;
    private String comment;
    private String actualValue;
    private boolean approved;
    private String type;
    private String whatFor;

    public static PendingTellMeWhenDto create(TellMeWhen tellMeWhen) {
        return new PendingTellMeWhenDto()
                .setId(tellMeWhen.getId())
                .setActualValue(tellMeWhen.getActualValue())
                .setApproved(tellMeWhen.isApproved())
                .setComment(tellMeWhen.getComment())
                .setName(tellMeWhen.getName())
                .setWhatFor(extractWhatFor(tellMeWhen))
                .setType(extractType(tellMeWhen));
    }

    private static String extractType(TellMeWhen tellMeWhen) {
        if (tellMeWhen instanceof SpecTellMeWhen) {
            return "spec";
        } else if (tellMeWhen instanceof WowClassTellMeWhen) {
            return "class";
        } else {
            return "unknown";
        }
    }

    private static String extractWhatFor(TellMeWhen tellMeWhen) {
        if (tellMeWhen instanceof SpecTellMeWhen) {
            return String.format("%s %s", ((SpecTellMeWhen) tellMeWhen).getSpec().getName(),((SpecTellMeWhen) tellMeWhen).getSpec().getWowClass().getName());
        } else if (tellMeWhen instanceof WowClassTellMeWhen) {
            return ((WowClassTellMeWhen) tellMeWhen).getWowClass().getName();
        } else {
            return "unknown";
        }
    }

    public Long getId() {
        return id;
    }

    public PendingTellMeWhenDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PendingTellMeWhenDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public PendingTellMeWhenDto setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getActualValue() {
        return actualValue;
    }

    public PendingTellMeWhenDto setActualValue(String actualValue) {
        this.actualValue = actualValue;
        return this;
    }

    public boolean isApproved() {
        return approved;
    }

    public PendingTellMeWhenDto setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public String getType() {
        return type;
    }

    public PendingTellMeWhenDto setType(String type) {
        this.type = type;
        return this;
    }

    public String getWhatFor() {
        return whatFor;
    }

    public PendingTellMeWhenDto setWhatFor(String whatFor) {
        this.whatFor = whatFor;
        return this;
    }
}
