package com.deswaef.weakauras.ui.tellmewhen.domain;

import com.deswaef.weakauras.classes.domain.Spec;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("SPEC")
public class SpecTellMeWhen extends TellMeWhen{

    @ManyToOne
    @JoinColumn(name = "spec_id")
    private Spec spec;

    public Spec getSpec() {
        return spec;
    }

    public SpecTellMeWhen setSpec(Spec spec) {
        this.spec = spec;
        return this;
    }

}
