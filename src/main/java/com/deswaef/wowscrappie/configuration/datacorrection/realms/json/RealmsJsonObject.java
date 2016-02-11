package com.deswaef.wowscrappie.configuration.datacorrection.realms.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RealmsJsonObject {
    private List<RealmJsonObject> realms;

    public List<RealmJsonObject> getRealms() {
        return realms;
    }

    public void setRealms(List<RealmJsonObject> realms) {
        this.realms = realms;
    }
}