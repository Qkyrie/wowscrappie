package com.deswaef.wowscrappie.auctionhouse.controller;

import com.deswaef.wowscrappie.auctionhouse.controller.dto.AuctionHouseTopItem;
import com.deswaef.wowscrappie.auctionhouse.service.AuctionHouseSnapshotService;
import com.deswaef.wowscrappie.realm.domain.Realm;
import com.deswaef.wowscrappie.realm.service.RealmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rest/auctionhouse/realm/{realmId}")
public class AuctionHouseRealmRestController {

    @Autowired
    private AuctionHouseSnapshotService auctionHouseSnapshotService;
    @Autowired
    private RealmService realmService;

    @RequestMapping(value = "/mostavailable")
    public List<AuctionHouseTopItem> mostAvailable(@PathVariable("realmId") Long realmId) {
        Optional<Realm> myRealm = realmService.findOne(realmId);

        if (myRealm.isPresent()) {
            return auctionHouseSnapshotService.findTopItemsByRealm(
                    myRealm.get(),
            (name, quantity) -> new AuctionHouseTopItem()
                    .setName(name)
                    .setTotalCount(quantity)
            );
        } else {
            return new ArrayList<>();
        }
    }


}
