package com.deswaef.weakauras.classes.service;

import com.deswaef.weakauras.classes.domain.Spec;
import com.deswaef.weakauras.classes.domain.WowClass;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SpecService {

    Set<Spec> byClass(WowClass wowClass);

    Optional<Spec> bySlug(WowClass wowClass, String slug);
    Optional<Spec> findByWarcraftlogsIdAndWowClassWarcraftlogsid(Long wclId, Long classWclId);
}
