package com.deswaef.weakauras.ui.reports.domain;

import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("TMW")
public class TellMeWhenReport extends InterfaceReport {

    @ManyToOne
    @JoinColumn(name = "tmw_id")
    private TellMeWhen tellMeWhen;

    public TellMeWhen getTellMeWhen() {
        return tellMeWhen;
    }

    public TellMeWhenReport setTellMeWhen(TellMeWhen tellMeWhen) {
        this.tellMeWhen = tellMeWhen;
        return this;
    }
}
