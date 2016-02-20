package com.deswaef.wowscrappie.realm.controller.dto;

import com.deswaef.wowscrappie.realm.domain.Realm;

public class RealmDto {
    private long id;
    private String name;
    private String fullName;
    private String locality;

    public static RealmDto create(Realm realm) {
        return new RealmDto()
                .setId(realm.getId())
                .setName(realm.getName())
                .setLocality(realm.getLocality().getLocalityName())
                .setFullName(realm.getLocality().getLocalityName() + "-" + realm.getName());
    }

    public long getId() {
        return id;
    }

    public RealmDto setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RealmDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public RealmDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getLocality() {
        return locality;
    }

    public RealmDto setLocality(String locality) {
        this.locality = locality;
        return this;
    }
}
