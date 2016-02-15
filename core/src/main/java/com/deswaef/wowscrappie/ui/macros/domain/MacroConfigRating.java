package com.deswaef.wowscrappie.ui.macros.domain;

import com.deswaef.wowscrappie.ui.rating.domain.ConfigRating;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("MACRO")
public class MacroConfigRating extends ConfigRating {

    @OneToOne
    @JoinColumn(name = "macro_id")
    private Macro macro;

    public Macro getMacro() {
        return macro;
    }

    public MacroConfigRating setMacro(Macro macro) {
        this.macro = macro;
        return this;
    }
}
