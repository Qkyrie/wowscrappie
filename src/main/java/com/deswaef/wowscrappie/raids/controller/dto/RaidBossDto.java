package com.deswaef.wowscrappie.raids.controller.dto;

import com.deswaef.wowscrappie.raids.domain.RaidBoss;

public class RaidBossDto {
    private String raidName;
    private long raidId;
    private String slug;
    private String name;
    private long id;

    public static RaidBossDto create(RaidBoss raidBoss) {
        return new RaidBossDto()
                .setId(raidBoss.getId())
                .setSlug(raidBoss.getSlug())
                .setName(raidBoss.getName())
                .setRaidId(raidBoss.getRaid().getId())
                .setRaidName(raidBoss.getRaid().getName());
    }

    public String getRaidName() {
        return raidName;
    }

    public RaidBossDto setRaidName(String raidName) {
        this.raidName = raidName;
        return this;
    }

    public long getRaidId() {
        return raidId;
    }

    public RaidBossDto setRaidId(long raidId) {
        this.raidId = raidId;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public RaidBossDto setSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public String getName() {
        return name;
    }

    public RaidBossDto setName(String name) {
        this.name = name;
        return this;
    }

    public long getId() {
        return id;
    }

    public RaidBossDto setId(long id) {
        this.id = id;
        return this;
    }
}
