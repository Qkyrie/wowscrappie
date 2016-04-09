package com.deswaef.wowscrappie.classes.domain;

public class SpecFixture {

    public static Spec frost_dk() {
        return new Spec()
                .setName("Frost")
                .setSlug("frost")
                .setId(1L)
                .setWowClass(WowClassFixture.dk())
                .setWarcraftlogsId(1L);
    }

}