package com.deswaef.wowscrappie.auctionhouse.repository;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

public interface AuctionItemRepository extends ElasticsearchRepository<AuctionItem, Long> {

    List<AuctionItem> findAllByItemAndRealmId(@Param("item") long item, @Param("realmId") long realm);

    Stream<AuctionItem> findAllByRealmIdAndExportTimeBetween(@Param("realmId") long realmId,
                                                             @Param("from") long startDate,
                                                             @Param("to") long endDate);

}
