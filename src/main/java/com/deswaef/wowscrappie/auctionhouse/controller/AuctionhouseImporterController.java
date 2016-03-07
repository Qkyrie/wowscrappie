package com.deswaef.wowscrappie.auctionhouse.controller;

import com.deswaef.heureka.battlenet.wow.continuous.BattlenetAuctionsImporter;
import com.deswaef.heureka.infrastructure.exception.HeurekaException;
import com.deswaef.wowscrappie.auctionhouse.analyzer.DailyAnalyzer;
import com.deswaef.wowscrappie.auctionhouse.continuous.AnalyzePreviousDayScheduler;
import com.deswaef.wowscrappie.auctionhouse.repository.AuctionItemNativeRepository;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/rest/auctionhouse/importing")
public class AuctionhouseImporterController {

    @Autowired
    private BattlenetAuctionsImporter importer;

    @Autowired
    private AnalyzePreviousDayScheduler dailyAnalyzer;
    @Autowired
    private AuctionItemNativeRepository reindexRepository;


    @RequestMapping("/dailyanalyzer")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> analyzePreviousDay() {
        dailyAnalyzer.analyzeForAPreviousDay(1);
        return ResponseEntity.ok("done");
    }


    @RequestMapping("/dailyanalyzer/{amount}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> analyzeAPreviousDay(@PathVariable("amount") int amount) {
        dailyAnalyzer.analyzeForAPreviousDay(amount);
        return ResponseEntity.ok("done");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = GET, value = "/{realmId}")
    public DeferredResult<ResponseEntity<String>> doImport(@PathVariable("realmId") long realmId) {
        DeferredResult<ResponseEntity<String>> result = new DeferredResult<>();

        Observable.<ResponseEntity<String>>create(o -> {
            try {
                importer.importAuctions(realmId);
                o.onNext(ResponseEntity.ok("done"));
            } catch (HeurekaException ex) {
                o.onError(ex);
            }
        }).subscribe(result::setResult,
                result::setErrorResult);

        return result;

    }

}
