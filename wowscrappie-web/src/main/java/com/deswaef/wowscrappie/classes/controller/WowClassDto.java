package com.deswaef.wowscrappie.classes.controller;

import com.deswaef.wowscrappie.classes.domain.WowClass;

public class WowClassDto {
    private Long id;
    private String name;
    private String slug;

    private Long waAmount = 0L;
    private Long tmwAmount = 0L;
    private Long macroAmount = 0L;

    public static WowClassDto fromWowClass(WowClass wowClass) {
        return new WowClassDto()
                .setId(wowClass.getId())
                .setSlug(wowClass.getSlug())
                .setName(wowClass.getName());
    }

    public Long getId() {
        return id;
    }

    public WowClassDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public WowClassDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public WowClassDto setSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public Long getWaAmount() {
        return waAmount;
    }

    public WowClassDto setWaAmount(Long waAmount) {
        this.waAmount = waAmount;
        return this;
    }

    public Long getTmwAmount() {
        return tmwAmount;
    }

    public WowClassDto setTmwAmount(Long tmwAmount) {
        this.tmwAmount = tmwAmount;
        return this;
    }

    public Long getMacroAmount() {
        return macroAmount;
    }

    public WowClassDto setMacroAmount(Long macroAmount) {
        this.macroAmount = macroAmount;
        return this;
    }
}
