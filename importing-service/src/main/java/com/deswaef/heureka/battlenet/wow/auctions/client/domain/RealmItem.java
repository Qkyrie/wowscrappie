package com.deswaef.heureka.battlenet.wow.auctions.client.domain;

public class RealmItem {
    private String name;
    private String slug;

    public String name() {
        return name;
    }

    public RealmItem name(String name) {
        this.name = name;
        return this;
    }

    public String slug() {
        return slug;
    }

    public RealmItem slug(String slug) {
        this.slug = slug;
        return this;
    }
}
