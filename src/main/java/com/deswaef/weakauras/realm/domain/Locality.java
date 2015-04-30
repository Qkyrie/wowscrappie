package com.deswaef.weakauras.realm.domain;

public enum Locality {
    EU("eu"), US("us");

    private String localityName;

    Locality(String localityName) {
        this.localityName = localityName;
    }

    public String getLocalityName() {
        return localityName;
    }
}
