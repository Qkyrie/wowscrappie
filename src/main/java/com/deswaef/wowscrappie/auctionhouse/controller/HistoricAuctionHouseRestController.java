package com.deswaef.wowscrappie.auctionhouse.controller;

import com.deswaef.wowscrappie.auctionhouse.domain.HistoricAuctionHouseSnapshot;
import com.deswaef.wowscrappie.auctionhouse.service.HistoricAuctionHouseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;

@RestController
@RequestMapping("/rest/auctionhouse")
public class HistoricAuctionHouseRestController {

    @Autowired
    private HistoricAuctionHouseDataService historicAuctionHouseDataService;

    @RequestMapping("/item/{item}/realm/{realm}")
    public DeferredResult<List<HistoricAuctionHouseSnapshot>> byItemAndRealm(@PathVariable("item") long item, @PathVariable("realm") long realm) {
        DeferredResult<List<HistoricAuctionHouseSnapshot>> returnValue = new DeferredResult<>();
        historicAuctionHouseDataService.findByItemIdAndRealm(item, realm)
                .toList()
                .subscribe(returnValue::setResult,
                        returnValue::setErrorResult);
        return returnValue;
    }

}
