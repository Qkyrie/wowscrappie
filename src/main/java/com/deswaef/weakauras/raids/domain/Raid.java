package com.deswaef.weakauras.raids.domain;

import javax.persistence.*;

@Entity
@Table(name = "raid")
public class Raid {

    @Id
    private Long id;
    @Column(name = "wcl_raid_id")
    private Long warcraftlogsRaidId;
    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @ManyToOne
    @JoinColumn(name = "tier_id")
    private Tier tier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public Long getWarcraftlogsRaidId() {
        return warcraftlogsRaidId;
    }

    public Raid setWarcraftlogsRaidId(Long warcraftlogsRaidId) {
        this.warcraftlogsRaidId = warcraftlogsRaidId;
        return this;
    }
}
