package com.deswaef.weakauras.ui.tellmewhen.domain;

import com.deswaef.weakauras.ui.rating.domain.ConfigRating;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("TMW")
public class TellMeWhenConfigRating extends ConfigRating {

    @OneToOne
    @JoinColumn(name = "tmw_id")
    private TellMeWhen tellMeWhen;

    public TellMeWhen getTellMeWhen() {
        return tellMeWhen;
    }

    public TellMeWhenConfigRating setTellMeWhen(TellMeWhen tellMeWhen) {
        this.tellMeWhen = tellMeWhen;
        return this;
    }
}
