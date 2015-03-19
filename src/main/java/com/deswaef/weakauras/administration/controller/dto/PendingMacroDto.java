package com.deswaef.weakauras.administration.controller.dto;

import com.deswaef.weakauras.ui.macros.domain.Macro;
import com.deswaef.weakauras.ui.macros.domain.SpecMacro;
import com.deswaef.weakauras.ui.macros.domain.WowClassMacro;

public class PendingMacroDto {
    private Long id;
    private String name;
    private String comment;
    private String actualValue;
    private boolean approved;
    private String type;
    private String whatFor;


    public static PendingMacroDto create(Macro macro) {
        return new PendingMacroDto()
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
        } else {
            return "unknown";
        }
    }

    private static String extractType(Macro macro) {
        if (macro instanceof SpecMacro) {
            return "spec";
        } else if (macro instanceof WowClassMacro) {
            return "class";
        } else {
            return "unknown";
        }
    }

    public Long getId() {
        return id;
    }

    public PendingMacroDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PendingMacroDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public PendingMacroDto setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getActualValue() {
        return actualValue;
    }

    public PendingMacroDto setActualValue(String actualValue) {
        this.actualValue = actualValue;
        return this;
    }

    public boolean isApproved() {
        return approved;
    }

    public PendingMacroDto setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public String getType() {
        return type;
    }

    public PendingMacroDto setType(String type) {
        this.type = type;
        return this;
    }

    public String getWhatFor() {
        return whatFor;
    }

    public PendingMacroDto setWhatFor(String whatFor) {
        this.whatFor = whatFor;
        return this;
    }
}
