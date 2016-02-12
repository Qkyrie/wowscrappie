package com.deswaef.wowscrappie.auctionhouse.repository;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshot;
import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;

public interface AuctionHouseSnapshotRepository extends JpaRepository<AuctionHouseSnapshot, Long> {
}
