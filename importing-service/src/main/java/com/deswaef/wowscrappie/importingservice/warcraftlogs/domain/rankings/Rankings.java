package com.deswaef.wowscrappie.importingservice.warcraftlogs.domain.rankings;

import com.deswaef.wowscrappie.importingservice.warcraftlogs.WarcraftlogsConversionException;
import com.deswaef.wowscrappie.importingservice.warcraftlogs.WarcraftlogsConverter;

import java.util.List;

public class Rankings {
    private Long total;
    private List<Ranking> rankings;

    public Long getTotal() {
        return total;
    }

    public Rankings setTotal(Long total) {
        this.total = total;
        return this;
    }

    public List<Ranking> getRankings() {
        return rankings;
    }

    public Rankings setRankings(List<Ranking> rankings) {
        this.rankings = rankings;
        return this;
    }

    public static Rankings fromJson(String json) throws WarcraftlogsConversionException {
        try {
            return WarcraftlogsConverter.convert(json, Rankings.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new WarcraftlogsConversionException();
        }
    }
}
