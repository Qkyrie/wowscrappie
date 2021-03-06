package com.deswaef.wowscrappie.ui.weakauras.domain;

import com.deswaef.wowscrappie.raids.domain.Boss;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("BOSS_FIGHT")
public class BossFightWeakAura extends WeakAura {

    @ManyToOne
    @JoinColumn(name = "boss_id")
    private Boss boss;

    public Boss getBoss() {
        return boss;
    }

    public BossFightWeakAura setBoss(Boss boss) {
        this.boss = boss;
        return this;
    }
}
