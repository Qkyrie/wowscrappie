package com.deswaef.wowscrappie.importingservice.warcraftlogs.service.searchcriteria;

import com.deswaef.wowscrappie.importingservice.warcraftlogs.domain.metrics.MetricEnum;
import com.deswaef.wowscrappie.realm.domain.Realm;

public class SearchCharacterRankingDto {
    private String characterName;
    private Realm realm;
    private MetricEnum metricEnum;

    public String getCharacterName() {
        return characterName;
    }

    public SearchCharacterRankingDto setCharacterName(String characterName) {
        this.characterName = characterName;
        return this;
    }


    public Realm getRealm() {
        return realm;
    }

    public SearchCharacterRankingDto setRealm(Realm realm) {
        this.realm = realm;
        return this;
    }

    public MetricEnum getMetricEnum() {
        return metricEnum;
    }

    public SearchCharacterRankingDto setMetricEnum(MetricEnum metricEnum) {
        this.metricEnum = metricEnum;
        return this;
    }
}
