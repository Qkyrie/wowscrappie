package com.deswaef.wowscrappie.configuration;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionItem;
import com.deswaef.wowscrappie.auctionhouse.domain.DailyAuctionSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.annotation.PostConstruct;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.deswaef")
public class ElasticSearchConfiguration {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @PostConstruct
    public void assureMappings() {
        if (!elasticsearchTemplate.indexExists(AuctionItem.class)) {
            elasticsearchTemplate.createIndex(AuctionItem.class);
            elasticsearchTemplate.putMapping(AuctionItem.class);
        }

        if (!elasticsearchTemplate.indexExists(DailyAuctionSnapshot.class)) {
            elasticsearchTemplate.createIndex(DailyAuctionSnapshot.class);
            elasticsearchTemplate.putMapping(DailyAuctionSnapshot.class);
        }
    }
}
