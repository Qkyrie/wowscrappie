package com.deswaef.weakauras.warcraftlogs.service;

import com.deswaef.weakauras.warcraftlogs.WarcraftlogsAPIClient;
import com.deswaef.weakauras.warcraftlogs.WarcraftlogsAPIException;
import com.deswaef.weakauras.warcraftlogs.domain.metrics.MetricEnum;
import com.deswaef.weakauras.warcraftlogs.domain.rankings.CharacterRanking;
import com.deswaef.weakauras.warcraftlogs.domain.rankings.CharacterRankings;
import com.deswaef.weakauras.warcraftlogs.service.searchcriteria.SearchCharacterRankingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class RankingsFetcher {

    public static final String SEARCH_USER_RANKS = "/rankings/character/%s/%s/%s";

    @Autowired
    private WarcraftlogsAPIClient warcraftlogsAPIClient;

    public Collection<CharacterRanking> findRankings(SearchCharacterRankingDto searchCharacterRankingDto) throws WarcraftlogsAPIException {
        Assert.notNull(searchCharacterRankingDto.getCharacterName(), "Character name should not be empty");
        Assert.notNull(searchCharacterRankingDto.getRealm(), "Realm should not be empty");

        if (searchCharacterRankingDto.getMetricEnum() == null) {
            searchCharacterRankingDto.setMetricEnum(MetricEnum.DPS);
        }
        return CharacterRankings.fromJson(warcraftlogsAPIClient.getFromAPI(getCharacterRankingsUrl(searchCharacterRankingDto)));
    }

    private String getCharacterRankingsUrl(SearchCharacterRankingDto searchCharacterRankingDto) {
        return String.format(SEARCH_USER_RANKS,
                searchCharacterRankingDto.getCharacterName(), searchCharacterRankingDto.getRealm().getSlug(),
                searchCharacterRankingDto.getRealm().getLocality().getLocalityName());
    }
}
