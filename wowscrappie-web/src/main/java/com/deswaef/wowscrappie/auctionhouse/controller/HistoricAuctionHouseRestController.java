package com.deswaef.wowscrappie.auctionhouse.controller;

import com.deswaef.wowscrappie.auctionhouse.controller.dto.AuctionHouseSnapshotDto;
import com.deswaef.wowscrappie.auctionhouse.service.DailyAuctionHouseSnapshotService;
import com.deswaef.wowscrappie.infrastructure.exception.WowscrappieException;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/rest/auctionhouse/historic")
public class HistoricAuctionHouseRestController {

    public static final int DAYS_TO_BE_CHECKED = 31;
    @Autowired
    private DailyAuctionHouseSnapshotService dailyAuctionHouseSnapshotService;

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Found")
    @ExceptionHandler(WowscrappieException.class)
    public void notFound() {
    }

    @RequestMapping("/item/{item}/realm/{realm}")
    public List<AuctionHouseSnapshotDto> dailyByItemAndRealm(@PathVariable("item") long item, @PathVariable("realm") long realm) {
        List<AuctionHouseSnapshotDto> returnValue = new ArrayList<>();
        List<AuctionHouseSnapshotDto> auctionhouseSnapshots = dailyAuctionHouseSnapshotService
                .lastDays(realm, item, DAYS_TO_BE_CHECKED)
                .map(AuctionHouseSnapshotDto::from)
                .collect(Collectors.toList());
        returnValue.addAll(auctionhouseSnapshots);

        LocalDate now = LocalDate.now();
        LocalDate xDaysAgo = now.minusDays(DAYS_TO_BE_CHECKED + 1);

        IntStream.rangeClosed(auctionhouseSnapshots.size(), DAYS_TO_BE_CHECKED)
                .forEach(value -> {
                            LocalDate currentDate = xDaysAgo.plusDays(value);
                            if (!returnValue.stream().anyMatch(x -> LocalDate.fromDateFields(x.getExportTime()).equals(currentDate))) {
                                returnValue.add(AuctionHouseSnapshotDto.empty(item, realm, currentDate.toDate()));
                            }
                        }

                );
        return returnValue
                .stream()
                .sorted((x, y) -> x.getExportTime().compareTo(y.getExportTime()))
                .collect(Collectors.toList());
    }

}

