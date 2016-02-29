package com.deswaef.wowscrappie.auctionhouse.repository;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionItem;
import com.deswaef.wowscrappie.auctionhouse.domain.ReadableAuctionItem;
import org.elasticsearch.index.query.BoolFilterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.elasticsearch.index.query.FilterBuilders.rangeFilter;
import static org.elasticsearch.index.query.FilterBuilders.termFilter;
import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;

@Repository
public class AuctionItemNativeRepository {

    private ElasticsearchTemplate client;

    @Autowired
    public AuctionItemNativeRepository(ElasticsearchTemplate client) {
        this.client = client;
    }

    public List<ReadableAuctionItem> findByRealmAndDate(long realm, long from, long to) {

        BoolFilterBuilder boolFilter = new BoolFilterBuilder();
        boolFilter.must(
                termFilter("realmId", realm)
        );
        boolFilter.must(
                rangeFilter("exportTime")
                        .from(from)
                        .to(to)
        );

        SearchQuery query = new NativeSearchQueryBuilder()
                .withPageable(new PageRequest(0, Integer.MAX_VALUE))
                .withFilter(boolFilter)
                .build();

        return this.client.queryForList(query, ReadableAuctionItem.class);
    }

    public void deleteBeforeDate(long date) {
        DeleteQuery deleteQuery = new DeleteQuery();
        deleteQuery.setQuery(
                rangeQuery("exportTime")
                        .from(0)
                        .to(date)
        );
        this.client.delete(deleteQuery, AuctionItem.class);
    }
}
