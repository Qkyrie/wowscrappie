package com.deswaef.wowscrappie.auctionhouse.controller;

import com.deswaef.wowscrappie.auctionhouse.controller.dto.AuctionHouseRegionStatisticDto;
import com.deswaef.wowscrappie.auctionhouse.controller.dto.AuctionHouseSnapshotDto;
import com.deswaef.wowscrappie.auctionhouse.service.AuctionHouseSnapshotRegionStatisticsService;
import com.deswaef.wowscrappie.auctionhouse.service.AuctionHouseSnapshotService;
import com.deswaef.wowscrappie.realm.domain.Locality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/rest/auctionhouse/latest")
public class CurrentAuctionHouseRestController {

    @Autowired
    private AuctionHouseSnapshotService auctionHouseSnapshotService;
    @Autowired
    private AuctionHouseSnapshotRegionStatisticsService auctionHouseSnapshotRegionStatisticsService;

    @RequestMapping(value = "/item/{item}/realm/{realm}", method = GET)
    public DeferredResult<AuctionHouseSnapshotDto> byItemAndRealm(@PathVariable("item") long item, @PathVariable("realm") long realm) {
        DeferredResult<AuctionHouseSnapshotDto> returnValue = new DeferredResult<>();
        auctionHouseSnapshotService.findByItemIdAndRealm(item, realm)
                .map(AuctionHouseSnapshotDto::from)
                .first()
                .subscribe(returnValue::setResult,
                        returnValue::setErrorResult);
        return returnValue;
    }

    @RequestMapping(value = "/item/{item}/locality/{locality}", method = GET)
    public AuctionHouseRegionStatisticDto byItemAndRegion(@PathVariable("item") long item, @PathVariable("locality") Locality locality) {
        return AuctionHouseRegionStatisticDto.from(auctionHouseSnapshotRegionStatisticsService.getStatisticsForRegion(item, locality));
    }

}
