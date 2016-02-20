package com.deswaef.wowscrappie.item.controller;

import com.deswaef.heureka.wowuction.client.WowuctionUrlBuilder;
import com.deswaef.wowscrappie.infrastructure.exception.WowscrappieException;
import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.item.service.ItemService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/rest/items")
public class ItemRestController {

    public static final long SILVERMOON_REALM_ID = 212L;
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("import-wowuction")
    private Job importWowuction;

    @Autowired
    private WowuctionUrlBuilder wowuctionUrlBuilder;

    @Autowired
    private ItemService itemService;

    @RequestMapping(method = GET)
    public DeferredResult<List<Item>> items() {
        DeferredResult<List<Item>> itemResult = new DeferredResult<>();
        itemService.findAll()
                .toList()
                .subscribe(
                        itemResult::setResult,
                        itemResult::setErrorResult
                );
        return itemResult;
    }

    @RequestMapping(method = GET, value = "/query")
    public DeferredResult<ResponseEntity<List<Item>>> itemQuery(@RequestParam("search") String query) {
        DeferredResult<ResponseEntity<List<Item>>> result = new DeferredResult<>();
        if (query != null && !query.isEmpty()) {
            itemService.findByNameQuery(query)
                    .toList()
                    .subscribe(
                            e -> result.setResult(ResponseEntity.ok(e)),
                            error -> result.setResult(ResponseEntity.ok(new ArrayList<>()))
                    );
        } else {
            result.setResult(ResponseEntity.ok(new ArrayList<>()));
        }
        return result;
    }


    @RequestMapping(value = "/import", method = GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DeferredResult<ResponseEntity<String>> startImport() {
        DeferredResult<ResponseEntity<String>> importResult = new DeferredResult<>();
        Observable.<ResponseEntity<String>>create(o -> {
            try {
                jobLauncher.run(importWowuction, getJobParameters());
                o.onNext(ResponseEntity.ok("done"));
                o.onCompleted();
            } catch (Exception ex) {
                o.onError(ex);
            }
        }).single().subscribe(
                importResult::setResult,
                importResult::setErrorResult
        );
        return importResult;
    }


    private JobParameters getJobParameters() {
        try {
            long realmId = SILVERMOON_REALM_ID;
            return new JobParametersBuilder()
                    .addString("uri", wowuctionUrlBuilder.build(realmId).get())
                    .addLong("realmId", realmId)
                    .addString("currentTime", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();
        } catch (Exception ex) {
            throw new WowscrappieException("Unable to start or complete the importjob", ex);
        }
    }
}