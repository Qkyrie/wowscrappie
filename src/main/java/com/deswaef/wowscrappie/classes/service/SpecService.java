package com.deswaef.wowscrappie.classes.service;

import com.deswaef.wowscrappie.classes.domain.Spec;
import com.deswaef.wowscrappie.classes.domain.WowClass;

import java.util.Optional;
import java.util.Set;

public interface SpecService {

    Set<Spec> byClass(WowClass wowClass);

    Optional<Spec> bySlug(WowClass wowClass, String slug);
    Optional<Spec> findByWarcraftlogsIdAndWowClassWarcraftlogsid(Long wclId, Long classWclId);
}
