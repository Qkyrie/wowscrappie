package com.deswaef.weakauras.ui.macros.domain;

import com.deswaef.weakauras.raids.domain.Boss;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("BOSS_FIGHT")
public class BossFightMacro extends Macro{

    @ManyToOne
    @JoinColumn(name = "boss_id")
    private Boss boss;

    public Boss getBoss() {
        return boss;
    }

    public BossFightMacro setBoss(Boss boss) {
        this.boss = boss;
        return this;
    }
}
