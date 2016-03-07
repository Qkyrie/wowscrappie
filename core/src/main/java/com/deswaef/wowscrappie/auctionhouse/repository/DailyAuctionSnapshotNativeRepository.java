package com.deswaef.wowscrappie.auctionhouse.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;

@Repository
public class DailyAuctionSnapshotNativeRepository {
    private ElasticsearchTemplate client;

    @Autowired
    public DailyAuctionSnapshotNativeRepository(ElasticsearchTemplate client) {
        this.client = client;
    }

    public void deleteEntriesBefore(LocalDate date) {
        DeleteQuery query = new DeleteQuery();
        query.setQuery(
                rangeQuery("exportTime")
                        .gt(0)
                        .lt(Date.from(date.atStartOfDay().atOffset(ZoneOffset.UTC).toInstant()).getTime())
        );
    }
}
