package com.deswaef.wowscrappie.applicationevent.service;

import com.deswaef.wowscrappie.applicationevent.ApplicationEvent;
import com.deswaef.wowscrappie.applicationevent.ApplicationEventTypeEnum;
import rx.Observable;


public interface ApplicationEventService {
    Observable<ApplicationEvent> findLast10();

    void create(ApplicationEventTypeEnum type, String message);
}
