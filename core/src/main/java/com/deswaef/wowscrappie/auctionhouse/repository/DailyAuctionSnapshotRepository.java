package com.deswaef.wowscrappie.auctionhouse.repository;


import com.deswaef.wowscrappie.auctionhouse.domain.DailyAuctionSnapshot;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DailyAuctionSnapshotRepository extends ElasticsearchRepository<DailyAuctionSnapshot, String> {
}
