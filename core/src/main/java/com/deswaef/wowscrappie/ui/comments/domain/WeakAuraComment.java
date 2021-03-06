package com.deswaef.wowscrappie.ui.comments.domain;

import com.deswaef.wowscrappie.ui.weakauras.domain.WeakAura;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("WA")
public class WeakAuraComment extends InterfaceComment {

    @ManyToOne
    @JoinColumn(name = "wa_id")
    private WeakAura weakAura;

    public WeakAura getWeakAura() {
        return weakAura;
    }

    public WeakAuraComment setWeakAura(WeakAura weakAura) {
        this.weakAura = weakAura;
        return this;
    }
}
