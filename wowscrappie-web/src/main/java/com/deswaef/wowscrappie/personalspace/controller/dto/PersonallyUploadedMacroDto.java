package com.deswaef.wowscrappie.personalspace.controller.dto;

import com.deswaef.wowscrappie.ui.macros.domain.BossFightMacro;
import com.deswaef.wowscrappie.ui.macros.domain.Macro;
import com.deswaef.wowscrappie.ui.macros.domain.SpecMacro;
import com.deswaef.wowscrappie.ui.macros.domain.WowClassMacro;

public class PersonallyUploadedMacroDto {
    private Long id;
    private String name;
    private String comment;
    private String actualValue;
    private boolean approved;
    private String type;
    private String whatFor;
    private long rating = 0;


    public static PersonallyUploadedMacroDto create(Macro macro) {
        return new PersonallyUploadedMacroDto()
                .setName(macro.getName())
                .setComment(macro.getComment())
                .setApproved(macro.isApproved())
                .setActualValue(macro.getActualValue())
                .setId(macro.getId())
                .setType(extractType(macro))
                .setWhatFor(extractFor(macro));
    }

    private static String extractFor(Macro macro) {
        if (macro instanceof SpecMacro) {
            return String.format("%s %s", ((SpecMacro) macro).getSpec().getName(), ((SpecMacro) macro).getSpec().getWowClass().getName());
        } else if (macro instanceof WowClassMacro) {
            return ((WowClassMacro) macro).getWowClass().getName();
        } else if (macro instanceof BossFightMacro) {
            return ((BossFightMacro) macro).getBoss().getName();
        } else {
            return "unknown";
        }
    }

    private static String extractType(Macro macro) {
        if (macro instanceof SpecMacro) {
            return "spec";
        } else if (macro instanceof WowClassMacro) {
            return "class";
        } else if(macro instanceof BossFightMacro) {
            return "bossfight";
        } else {
            return "unknown";
        }
    }

    public Long getId() {
        return id;
    }

    public PersonallyUploadedMacroDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PersonallyUploadedMacroDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public PersonallyUploadedMacroDto setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getActualValue() {
        return actualValue;
    }

    public PersonallyUploadedMacroDto setActualValue(String actualValue) {
        this.actualValue = actualValue;
        return this;
    }

    public boolean isApproved() {
        return approved;
    }

    public PersonallyUploadedMacroDto setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public String getType() {
        return type;
    }

    public PersonallyUploadedMacroDto setType(String type) {
        this.type = type;
        return this;
    }

    public String getWhatFor() {
        return whatFor;
    }

    public PersonallyUploadedMacroDto setWhatFor(String whatFor) {
        this.whatFor = whatFor;
        return this;
    }

    public long getRating() {
        return rating;
    }

    public PersonallyUploadedMacroDto setRating(long rating) {
        this.rating = rating;
        return this;
    }
}
