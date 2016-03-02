package com.deswaef.wowscrappie.auctionhouse.repository;

import com.deswaef.wowscrappie.applicationevent.ApplicationEventTypeEnum;
import com.deswaef.wowscrappie.applicationevent.service.ApplicationEventService;
import com.deswaef.wowscrappie.auctionhouse.domain.AuctionItem;
import com.deswaef.wowscrappie.auctionhouse.domain.ReadableAuctionItem;
import org.elasticsearch.index.query.BoolFilterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.elasticsearch.index.query.FilterBuilders.rangeFilter;
import static org.elasticsearch.index.query.FilterBuilders.termFilter;
import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;

@Repository
public class AuctionItemNativeRepository {

    private ElasticsearchTemplate client;
    private ApplicationEventService applicationEventService;

    @Autowired
    private AuctionItemRepository auctionItemRepository;

    @Autowired
    public AuctionItemNativeRepository(ElasticsearchTemplate client,
                                       ApplicationEventService applicationEventService) {
        this.client = client;
        this.applicationEventService = applicationEventService;
    }


    public void reindexFromAuctionitemToAuctionitemEntry() {
        applicationEventService.create(
                ApplicationEventTypeEnum.JOB_STARTED,
                "Started Reindexing"
        );
        BoolFilterBuilder builder = new BoolFilterBuilder();
        builder.must(
                rangeFilter("exportTime")
                        .lte(new Date().getTime())
        );

        SearchQuery query = new NativeSearchQueryBuilder()
                .withPageable(new PageRequest(0, 50000))
                .withIndices("auction_item")
                .withTypes("auction_item")
                .withFilter(builder)
                .build();

        String scrollId = this.client.scan(query, 100000, false);
        boolean hasRecords = true;
        while (hasRecords) {
            Page<AuctionItem> page = this.client.scroll(scrollId, 500000, AuctionItem.class);
            if (page.hasContent()) {
                auctionItemRepository
                        .save(
                                page.getContent()
                        );
            } else {
                hasRecords = false;
            }
        }

        applicationEventService.create(
                ApplicationEventTypeEnum.JOB_ENDED,
                "Done Reindexing"
        );
    }

    public List<ReadableAuctionItem> findByRealmAndDate(long realm, long from, long to) {
        applicationEventService.create(
                ApplicationEventTypeEnum.JOB_STEP_STARTED,
                "starting to read ES daily_auction"
        );

        BoolFilterBuilder boolFilter = new BoolFilterBuilder();
        boolFilter.must(
                termFilter("realmId", realm)
                        .cache(false)
        );
        boolFilter.must(
                rangeFilter("exportTime")
                        .gte(from)
                        .lt(to)
                        .cache(true)
        );

        SearchQuery query = new NativeSearchQueryBuilder()
                .withPageable(new PageRequest(0, 50000))
                .withIndices("auction_item_entry")
                .withTypes("auction_item_entry")
                .withFields("buyout", "bid", "quantity", "item", "auctionId")
                .withFilter(boolFilter)
                .build();

        String scrollId = this.client.scan(query, 100000, false);
        List<ReadableAuctionItem> items = new ArrayList<>();
        boolean hasRecords = true;
        while (hasRecords) {
            applicationEventService.create(ApplicationEventTypeEnum.ES_PAGE_START_READ,
                    "start reading page from elasticsearch");

            Page<ReadableAuctionItem> page = this.client.scroll(scrollId, 500000, ReadableAuctionItem.class);
            if (page.hasContent()) {
                items.addAll(page.getContent());
                applicationEventService.create(ApplicationEventTypeEnum.ES_PAGE_STOP_READ,
                        "done reading page from elasticsearch");
            } else {
                hasRecords = false;
            }
        }
        applicationEventService.create(
                ApplicationEventTypeEnum.JOB_STEP_ENDED,
                "done reading ES daily_auction"
        );
        return items;
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
