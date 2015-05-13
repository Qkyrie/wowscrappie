package com.deswaef.weakauras.battlenet.api.domain;

import java.io.Serializable;

public class BattlenetProfile implements Serializable {
    private Long id;
    private String battletag;


    public Long getId() {
        return id;
    }

    public BattlenetProfile setId(Long id) {
        this.id = id;
        return this;
    }

    public String getBattletag() {
        return battletag;
    }

    public BattlenetProfile setBattletag(String battletag) {
        this.battletag = battletag;
        return this;
    }
}
