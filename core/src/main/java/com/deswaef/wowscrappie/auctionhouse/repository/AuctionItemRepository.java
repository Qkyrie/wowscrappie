package com.deswaef.wowscrappie.auctionhouse.repository;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AuctionItemRepository extends ElasticsearchRepository<AuctionItem, Long>{
}
