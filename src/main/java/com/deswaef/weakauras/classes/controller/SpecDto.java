package com.deswaef.weakauras.classes.controller;

import com.deswaef.weakauras.classes.domain.Spec;

import javax.persistence.Column;
import javax.persistence.Id;

public class SpecDto {

    private Long id;
    private String name;
    private String slug;

    private Long waAmount = 0L;
    private Long tmwAmount = 0L;
    private Long macroAmount = 0L;

    public static SpecDto fromSpec(Spec spec) {
        return new SpecDto()
                .setId(spec.getId())
                .setName(spec.getName())
                .setSlug(spec.getSlug());
    }

    public Long getId() {
        return id;
    }

    public SpecDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SpecDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public SpecDto setSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public Long getWaAmount() {
        return waAmount;
    }

    public SpecDto setWaAmount(Long waAmount) {
        this.waAmount = waAmount;
        return this;
    }

    public Long getTmwAmount() {
        return tmwAmount;
    }

    public SpecDto setTmwAmount(Long tmwAmount) {
        this.tmwAmount = tmwAmount;
        return this;
    }

    public Long getMacroAmount() {
        return macroAmount;
    }

    public SpecDto setMacroAmount(Long macroAmount) {
        this.macroAmount = macroAmount;
        return this;
    }
}
