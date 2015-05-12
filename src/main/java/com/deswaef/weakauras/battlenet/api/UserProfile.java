package com.deswaef.weakauras.battlenet.api;

import java.io.Serializable;

public class UserProfile implements Serializable {
    private Long id;
    private String battletag;


    public Long getId() {
        return id;
    }

    public UserProfile setId(Long id) {
        this.id = id;
        return this;
    }

    public String getBattletag() {
        return battletag;
    }

    public UserProfile setBattletag(String battletag) {
        this.battletag = battletag;
        return this;
    }
}
