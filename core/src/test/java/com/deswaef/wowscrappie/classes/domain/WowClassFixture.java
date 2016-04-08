package com.deswaef.wowscrappie.classes.domain;

public class WowClassFixture {

    public static WowClass dk() {
        return new WowClass()
                .setId(1L)
                .setName("Death Knight")
                .setSlug("death_knight")
                .setWarcraftlogsId(1L);
    }

}