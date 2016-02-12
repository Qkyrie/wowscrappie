package com.deswaef.wowscrappie.auctionhouse.repository;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshotConfiguration;
import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AuctionHouseSnapshotConfigurationRepository extends JpaRepository<AuctionHouseSnapshotConfiguration, Long> {

    Optional<AuctionHouseSnapshotConfiguration> findByRealmId(@Param("id") Long realmId);

}
