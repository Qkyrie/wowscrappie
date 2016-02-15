package com.deswaef.wowscrappie.realm.controller;

import com.deswaef.wowscrappie.realm.domain.Realm;
import com.deswaef.wowscrappie.realm.service.RealmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/rest/realms")
public class RealmRestController {

    @Autowired
    private RealmService realmService;

    @RequestMapping(method = GET)
    public DeferredResult<List<Realm>> realms() {
        DeferredResult<List<Realm>> realms = new DeferredResult<>();
        realmService.findAll()
                .toList()
                .subscribe(realms::setResult);
        return realms;
    }
}
