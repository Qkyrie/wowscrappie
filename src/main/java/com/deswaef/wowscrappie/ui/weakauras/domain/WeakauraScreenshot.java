package com.deswaef.wowscrappie.ui.weakauras.domain;

import com.deswaef.wowscrappie.ui.image.domain.Screenshot;

import javax.persistence.*;

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
