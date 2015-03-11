package com.deswaef.weakauras.ui.weakauras.domain;

import com.deswaef.weakauras.ui.rating.domain.ConfigRating;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("WA")
public class WeakAuraConfigRating extends ConfigRating {

    @OneToOne
    @JoinColumn(name = "wa_id")
    private WeakAura weakAura;

    public WeakAura getWeakAura() {
        return weakAura;
    }

    public WeakAuraConfigRating setWeakAura(WeakAura weakAura) {
        this.weakAura = weakAura;
        return this;
    }
}
