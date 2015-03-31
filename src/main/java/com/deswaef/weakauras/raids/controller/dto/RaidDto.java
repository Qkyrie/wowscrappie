package com.deswaef.weakauras.raids.controller.dto;

import com.deswaef.weakauras.raids.domain.Raid;

public class RaidDto {
    private long id;
    private String tierName;
    private String name;
    private String slug;

    public static RaidDto create(Raid raid) {
        return new RaidDto()
                .setId(raid.getId())
                .setName(raid.getName())
                .setTierName(raid.getTier().getName())
                .setSlug(raid.getSlug());
    }

    public long getId() {
        return id;
    }

    public RaidDto setId(long id) {
        this.id = id;
        return this;
    }

    public String getTierName() {
        return tierName;
    }

    public RaidDto setTierName(String tierName) {
        this.tierName = tierName;
        return this;
    }

    public String getName() {
        return name;
    }

    public RaidDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public RaidDto setSlug(String slug) {
        this.slug = slug;
        return this;
    }
}
