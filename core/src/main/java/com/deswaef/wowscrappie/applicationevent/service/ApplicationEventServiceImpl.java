package com.deswaef.wowscrappie.applicationevent.service;

import com.deswaef.wowscrappie.applicationevent.ApplicationEvent;
import com.deswaef.wowscrappie.applicationevent.ApplicationEventTypeEnum;
import com.deswaef.wowscrappie.applicationevent.repository.ApplicationEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rx.Observable;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class ApplicationEventServiceImpl implements ApplicationEventService {

    @Autowired
    private ApplicationEventRepository applicationEventRepository;

    @Override
    @Transactional(readOnly = true)
    public Observable<ApplicationEvent> findLast10() {
        return Observable.from(applicationEventRepository.findTop10ByOrderByEventTimeDesc()::iterator);
    }

    @Override
    @Transactional
    public void create(ApplicationEventTypeEnum type, String message) {
        applicationEventRepository
                .saveAndFlush(
                        new ApplicationEvent()
                                .setEventTime(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)))
                                .setEventType(type)
                                .setMessage(message)
                );

    }

}
