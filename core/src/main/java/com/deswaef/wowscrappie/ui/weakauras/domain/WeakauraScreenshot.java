package com.deswaef.wowscrappie.ui.weakauras.domain;

import com.deswaef.wowscrappie.ui.image.domain.Screenshot;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("wa")
public class WeakauraScreenshot extends Screenshot {

    @ManyToOne
    @JoinColumn(name = "wa_id")
    private WeakAura weakAura;

    public WeakAura getWeakAura() {
        return weakAura;
    }

    public WeakauraScreenshot setWeakAura(WeakAura weakAura) {
        this.weakAura = weakAura;
        return this;
    }
}
