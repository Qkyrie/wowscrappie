package com.deswaef.wowscrappie.ui.macros.domain;

import com.deswaef.wowscrappie.classes.domain.WowClass;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("WOWCLASS")
public class WowClassMacro extends Macro {
    @ManyToOne
    @JoinColumn(name = "wowclass_id")
    private WowClass wowClass;

    public WowClass getWowClass() {
        return wowClass;
    }

    public WowClassMacro setWowClass(WowClass wowClass) {
        this.wowClass = wowClass;
        return this;
    }
}
