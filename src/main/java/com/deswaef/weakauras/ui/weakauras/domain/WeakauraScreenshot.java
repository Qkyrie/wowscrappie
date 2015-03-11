package com.deswaef.weakauras.ui.weakauras.domain;

import com.deswaef.weakauras.ui.image.domain.Screenshot;

import javax.persistence.*;
import javax.validation.constraints.Size;

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
