package com.deswaef.wowscrappie.personalspace.controller.dto;

import com.deswaef.wowscrappie.ui.tellmewhen.domain.BossFightTellMeWhen;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.SpecTellMeWhen;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.WowClassTellMeWhen;

public class PersonallyUploadedTellMeWhenDto {

    private Long id;
    private String name;
    private String comment;
    private String actualValue;
    private boolean approved;
    private String type;
    private String whatFor;
    private long rating = 0;

    public static PersonallyUploadedTellMeWhenDto create(TellMeWhen tellMeWhen) {
        return new PersonallyUploadedTellMeWhenDto()
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
        } else if (tellMeWhen instanceof BossFightTellMeWhen) {
            return "bossfight";
        } else {
            return "unknown";
        }
    }

    private static String extractWhatFor(TellMeWhen tellMeWhen) {
        if (tellMeWhen instanceof SpecTellMeWhen) {
            return String.format("%s %s", ((SpecTellMeWhen) tellMeWhen).getSpec().getName(),((SpecTellMeWhen) tellMeWhen).getSpec().getWowClass().getName());
        } else if (tellMeWhen instanceof WowClassTellMeWhen) {
            return ((WowClassTellMeWhen) tellMeWhen).getWowClass().getName();
        } else if (tellMeWhen instanceof BossFightTellMeWhen) {
            return ((BossFightTellMeWhen) tellMeWhen).getBoss().getName();
        } else {
            return "unknown";
        }
    }

    public Long getId() {
        return id;
    }

    public PersonallyUploadedTellMeWhenDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PersonallyUploadedTellMeWhenDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public PersonallyUploadedTellMeWhenDto setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getActualValue() {
        return actualValue;
    }

    public PersonallyUploadedTellMeWhenDto setActualValue(String actualValue) {
        this.actualValue = actualValue;
        return this;
    }

    public boolean isApproved() {
        return approved;
    }

    public PersonallyUploadedTellMeWhenDto setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public String getType() {
        return type;
    }

    public PersonallyUploadedTellMeWhenDto setType(String type) {
        this.type = type;
        return this;
    }

    public String getWhatFor() {
        return whatFor;
    }

    public PersonallyUploadedTellMeWhenDto setWhatFor(String whatFor) {
        this.whatFor = whatFor;
        return this;
    }

    public long getRating() {
        return rating;
    }

    public PersonallyUploadedTellMeWhenDto setRating(long rating) {
        this.rating = rating;
        return this;
    }
}
