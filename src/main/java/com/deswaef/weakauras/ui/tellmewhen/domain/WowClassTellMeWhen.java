package com.deswaef.weakauras.ui.tellmewhen.domain;

import com.deswaef.weakauras.classes.domain.WowClass;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("WOWCLASS")
public class WowClassTellMeWhen extends TellMeWhen {

    @ManyToOne
    @JoinColumn(name = "wowclass_id")
    private WowClass wowClass;

    public WowClass getWowClass() {
        return wowClass;
    }

    public WowClassTellMeWhen setWowClass(WowClass wowClass) {
        this.wowClass = wowClass;
        return this;
    }
}
