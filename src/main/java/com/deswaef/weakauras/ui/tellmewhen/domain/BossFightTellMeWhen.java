package com.deswaef.weakauras.ui.tellmewhen.domain;

import com.deswaef.weakauras.raids.domain.Boss;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("BOSS_FIGHT")
public class BossFightTellMeWhen extends TellMeWhen {

    @ManyToOne
    @JoinColumn(name = "boss_id")
    private Boss boss;

    public Boss getBoss() {
        return boss;
    }

    public BossFightTellMeWhen setBoss(Boss boss) {
        this.boss = boss;
        return this;
    }
}
