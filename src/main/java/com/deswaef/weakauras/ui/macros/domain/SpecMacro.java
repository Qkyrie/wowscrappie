package com.deswaef.weakauras.ui.macros.domain;

import com.deswaef.weakauras.classes.domain.Spec;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("SPEC")
public class SpecMacro extends Macro {

    @ManyToOne
    @JoinColumn(name = "wowspec_id")
    private Spec spec;

    public Spec getSpec() {
        return spec;
    }

    public SpecMacro setSpec(Spec spec) {
        this.spec = spec;
        return this;
    }
}
