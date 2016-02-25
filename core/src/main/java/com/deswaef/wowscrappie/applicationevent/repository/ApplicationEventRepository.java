package com.deswaef.wowscrappie.applicationevent.repository;

import com.deswaef.wowscrappie.applicationevent.ApplicationEvent;
import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;

import java.util.stream.Stream;

public interface ApplicationEventRepository extends JpaRepository<ApplicationEvent, Long> {

    Stream<ApplicationEvent> findTop10ByOrderByEventTimeDesc();

}
