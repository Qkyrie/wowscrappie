package com.deswaef.wowscrappie.auctionhouse.repository;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshot;
import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.realm.domain.Realm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AuctionHouseSnapshotRepository extends JpaRepository<AuctionHouseSnapshot, Long> {

    List<AuctionHouseSnapshot> findByExportTimeBeforeAndRealm(@Param("exportTime") Date exportTime, @Param("realm") Realm realm);

    @Query("select distinct (aucss.exportTime) from AuctionHouseSnapshot aucss where realm = :realm")
    List<Date> findDistinctExportTimeByRealm(@Param("realm") Realm realm);

    Optional<AuctionHouseSnapshot> findFirstByItemAndRealm(@Param("item")Item item, @Param("realm") Realm realm);

}
