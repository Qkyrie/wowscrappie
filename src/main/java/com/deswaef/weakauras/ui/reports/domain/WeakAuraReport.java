package com.deswaef.weakauras.ui.reports.domain;

import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("WA")
public class WeakAuraReport extends InterfaceReport {

    @ManyToOne
    @JoinColumn(name = "wa_id")
    private WeakAura weakAura;

    public WeakAura getWeakAura() {
        return weakAura;
    }

    public WeakAuraReport setWeakAura(WeakAura weakAura) {
        this.weakAura = weakAura;
        return this;
    }
}
