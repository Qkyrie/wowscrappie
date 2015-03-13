package com.deswaef.weakauras.ui.weakauras.service;

import com.deswaef.weakauras.classes.domain.Spec;
import com.deswaef.weakauras.classes.domain.WowClass;
import com.deswaef.weakauras.infrastructure.service.OnRoleDependable;
import com.deswaef.weakauras.ui.image.domain.Screenshot;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;

import java.util.List;
import java.util.Optional;

public interface WeakAuraService extends OnRoleDependable {

    WeakAura createWeakAura(WeakAura weakAura);

    List<WeakAura> findByWowClass(WowClass wowClass);

    List<WeakAura> findBySpec(Spec spec);

    Long countByWowClass(WowClass wowClass);

    Long countBySpec(Spec spec);

    Optional<WeakAura> byId(Long id);

    long count();

    void saveScreenshot(String reference, WeakAura weakAura);

    List<Screenshot> findScreenshots(WeakAura weakAura);

    List<WeakAura> findAll();

    void approve(Long id);

    void delete(Long id);

    void disable(Long id);
}
