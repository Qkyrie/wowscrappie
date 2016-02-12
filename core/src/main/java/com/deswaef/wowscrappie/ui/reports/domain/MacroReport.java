package com.deswaef.wowscrappie.ui.reports.domain;

import com.deswaef.wowscrappie.ui.macros.domain.Macro;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("MACRO")
public class MacroReport extends InterfaceReport {

    @ManyToOne
    @JoinColumn(name = "macro_id")
    private Macro macro;

    public Macro getMacro() {
        return macro;
    }

    public MacroReport setMacro(Macro macro) {
        this.macro = macro;
        return this;
    }
}
