package com.deswaef.wowscrappie.realm.controller;

import com.deswaef.wowscrappie.infrastructure.mvc.HttpServletRequestProxy;
import com.deswaef.wowscrappie.realm.domain.Realm;
import com.deswaef.wowscrappie.realm.service.RealmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/realm")
public class RealmController {

    @Autowired
    private HttpServletRequestProxy requestProxy;
    @Autowired
    private RealmService realmService;


    @RequestMapping(value = "/choose", method = GET)
    public String pickRealmIndex() {
        return "realms/choose";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/choose/{realmId}")
    @ResponseBody
    public ResponseEntity pickDefaultRealm(@PathVariable("realmId") long realmId) {
        Optional<Realm> foundRealm = realmService.findOne(realmId);
        if (foundRealm.isPresent()) {
            chooseRealm(foundRealm.get());
            return ResponseEntity.ok(foundRealm.get());
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/current")
    @ResponseBody
    public ResponseEntity realm() {
        if (currentlyChosenRealm().isPresent()) {
            return ResponseEntity.ok(currentlyChosenRealm().get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    private void chooseRealm(Realm realm) {
        requestProxy.getHttpServletRequest().getSession(true)
                .setAttribute("com.wowscrappie.default-realm", realm);
    }

    private Optional<Realm> currentlyChosenRealm() {
        Object defaultRealm = requestProxy.getHttpServletRequest().getSession(true).getAttribute("com.wowscrappie.default-realm");
        if (defaultRealm != null) {
            return Optional.of((Realm) defaultRealm);
        } else {
            return Optional.empty();
        }
    }

}

