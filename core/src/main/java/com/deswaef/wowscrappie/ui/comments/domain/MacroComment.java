package com.deswaef.wowscrappie.ui.comments.domain;

import com.deswaef.wowscrappie.ui.macros.domain.Macro;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("MACRO")
public class MacroComment extends InterfaceComment {

    @ManyToOne
    @JoinColumn(name = "macro_id")
    private Macro macro;

    public Macro getMacro() {
        return macro;
    }

    public MacroComment setMacro(Macro macro) {
        this.macro = macro;
        return this;
    }
}
