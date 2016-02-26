package com.deswaef.wowscrappie.auctionhouse.controller;

import com.deswaef.wowscrappie.auctionhouse.controller.dto.AuctionHouseSnapshotDto;
import com.deswaef.wowscrappie.auctionhouse.service.DailyAuctionHouseSnapshotService;
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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/auctionhouse/historic")
public class HistoricAuctionHouseRestController {

    @Autowired
    private HistoricAuctionHouseDataService historicAuctionHouseDataService;
    @Autowired
    private DailyAuctionHouseSnapshotService dailyAuctionHouseSnapshotService;

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Found")
    @ExceptionHandler(WowscrappieException.class)
    public void notFound() {
    }

    //temporarily shut down, there's no limit at this point
    //@RequestMapping("/item/{item}/realm/{realm}")
    public DeferredResult<List<AuctionHouseSnapshotDto>> historicByItemAndRealm(@PathVariable("item") long item, @PathVariable("realm") long realm) {
        DeferredResult<List<AuctionHouseSnapshotDto>> returnValue = new DeferredResult<>();
        historicAuctionHouseDataService.findByItemIdAndRealm(item, realm)
                .map(AuctionHouseSnapshotDto::from)
                .toList()
                .subscribe(returnValue::setResult,
                        returnValue::setErrorResult);
        return returnValue;
    }

    @RequestMapping("/item/{item}/realm/{realm}/daily")
    public DeferredResult<List<AuctionHouseSnapshotDto>> dailyByItemAndRealm(@PathVariable("item") long item,
                                                                             @PathVariable("realm") long realm) {
        DeferredResult<List<AuctionHouseSnapshotDto>> returnValue = new DeferredResult<>();
        returnValue.setResult(
                dailyAuctionHouseSnapshotService
                        .lastDays(realm, item, 6)
                        .map(AuctionHouseSnapshotDto::from)
                        .collect(Collectors.toList())
        );
        return returnValue;
    }
}

