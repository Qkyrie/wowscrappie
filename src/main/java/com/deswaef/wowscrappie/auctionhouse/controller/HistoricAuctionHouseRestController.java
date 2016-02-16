package com.deswaef.wowscrappie.auctionhouse.controller;

import com.deswaef.wowscrappie.auctionhouse.domain.AuctionHouseSnapshot;
import com.deswaef.wowscrappie.auctionhouse.domain.HistoricAuctionHouseSnapshot;
import com.deswaef.wowscrappie.auctionhouse.service.AuctionHouseSnapshotService;
import com.deswaef.wowscrappie.auctionhouse.service.HistoricAuctionHouseDataService;
import com.deswaef.wowscrappie.infrastructure.exception.WowscrappieException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;

@RestController
@RequestMapping("/rest/auctionhouse")
public class HistoricAuctionHouseRestController {

    @Autowired
    private HistoricAuctionHouseDataService historicAuctionHouseDataService;
    @Autowired
    private AuctionHouseSnapshotService auctionHouseSnapshotService;

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Not Found")
    @ExceptionHandler(WowscrappieException.class)
    public void notFound() {
    }

    @RequestMapping("/historic/item/{item}/realm/{realm}")
    public DeferredResult<List<HistoricAuctionHouseSnapshot>> historicByItemAndRealm(@PathVariable("item") long item, @PathVariable("realm") long realm) {
        DeferredResult<List<HistoricAuctionHouseSnapshot>> returnValue = new DeferredResult<>();
        historicAuctionHouseDataService.findByItemIdAndRealm(item, realm)
                .toList()
                .subscribe(returnValue::setResult,
                        returnValue::setErrorResult);
        return returnValue;
    }


    @RequestMapping("/latest/item/{item}/realm/{realm}")
    public DeferredResult<AuctionHouseSnapshot> byItemAndRealm(@PathVariable("item") long item, @PathVariable("realm") long realm) {
        DeferredResult<AuctionHouseSnapshot> returnValue = new DeferredResult<>();
        auctionHouseSnapshotService.findByItemIdAndRealm(item, realm)
                .subscribe(returnValue::setResult,
                        returnValue::setErrorResult);
        return returnValue;
    }

}
