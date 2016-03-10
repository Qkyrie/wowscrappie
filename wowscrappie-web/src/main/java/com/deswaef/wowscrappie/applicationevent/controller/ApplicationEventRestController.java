package com.deswaef.wowscrappie.applicationevent.controller;

import com.deswaef.wowscrappie.applicationevent.service.ApplicationEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/rest/application/events")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ApplicationEventRestController {

    @Autowired
    private ApplicationEventService applicationEventService;

    @RequestMapping(method = GET)
    public DeferredResult<ResponseEntity> last10() {
        DeferredResult<ResponseEntity> result = new DeferredResult<>();
        applicationEventService
                .findLast10()
                .map(ApplicationEventDto::from)
                .toList()
                .subscribe(x -> result.setResult(ResponseEntity.ok(x)),
                        x -> result.setErrorResult(ResponseEntity.ok(new ArrayList<>())));
        return result;
    }

}
