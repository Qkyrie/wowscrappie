package com.deswaef.wowscrappie.raids.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("RAIDBOSS")
public class RaidBoss extends Boss {

    @ManyToOne
    @JoinColumn(name = "raid_id")
    private Raid raid;

    public Raid getRaid() {
        return raid;
    }

    public void setRaid(Raid raid) {
        this.raid = raid;
    }
}
