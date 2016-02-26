package com.deswaef.wowscrappie.auctionhouse.repository;

import com.deswaef.wowscrappie.auctionhouse.domain.DailyAuctionSnapshot;
import com.deswaef.wowscrappie.auctionhouse.domain.HistoricAuctionHouseSnapshot;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoricAuctionHouseSnapshotRepository extends ElasticsearchRepository<HistoricAuctionHouseSnapshot, String> {

    List<HistoricAuctionHouseSnapshot> findByItemId(@Param("itemId") long itemId);

    List<HistoricAuctionHouseSnapshot> findByItemIdAndRealmId(@Param("itemId") long itemId, @Param("realmId") long realmId);

}
