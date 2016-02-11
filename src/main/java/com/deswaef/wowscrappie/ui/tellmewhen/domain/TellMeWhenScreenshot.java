package com.deswaef.wowscrappie.ui.tellmewhen.domain;

import com.deswaef.wowscrappie.ui.image.domain.Screenshot;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("tmw")
public class TellMeWhenScreenshot extends Screenshot {

    @ManyToOne
    @JoinColumn(name = "tmw_id")
    private TellMeWhen tellMeWhen;

    public TellMeWhen getTellMeWhen() {
        return tellMeWhen;
    }

    public TellMeWhenScreenshot setTellMeWhen(TellMeWhen tellMeWhen) {
        this.tellMeWhen = tellMeWhen;
        return this;
    }
}
