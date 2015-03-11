package com.deswaef.weakauras.ui.weakauras.domain;

import com.deswaef.weakauras.classes.domain.Spec;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("SPEC")
public class SpecWeakAura extends WeakAura {

    @ManyToOne
    @JoinColumn(name = "spec_id")
    private Spec spec;

    public Spec getSpec() {
        return spec;
    }

    public SpecWeakAura setSpec(Spec spec) {
        this.spec = spec;
        return this;
    }
}
