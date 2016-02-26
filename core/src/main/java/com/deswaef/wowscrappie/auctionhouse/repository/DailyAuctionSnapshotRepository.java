package com.deswaef.wowscrappie.auctionhouse.repository;


import com.deswaef.wowscrappie.auctionhouse.domain.DailyAuctionSnapshot;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.query.Param;

import java.util.stream.Stream;

public interface DailyAuctionSnapshotRepository extends ElasticsearchRepository<DailyAuctionSnapshot, String> {

    Stream<DailyAuctionSnapshot> findAllByRealmIdAndItemIdAndExportTimeBetween(@Param("realmId") long realmId,
                                                                               @Param("itemId") long itemId,
                                                                               @Param("from") long startDate,
                                                                               @Param("to") long endDate);

}
