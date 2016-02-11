package com.deswaef.wowscrappie.ui.weakauras.service;

import com.deswaef.wowscrappie.classes.domain.Spec;
import com.deswaef.wowscrappie.classes.domain.WowClass;
import com.deswaef.wowscrappie.infrastructure.service.OnRoleDependable;
import com.deswaef.wowscrappie.raids.domain.Boss;
import com.deswaef.wowscrappie.ui.image.domain.Screenshot;
import com.deswaef.wowscrappie.ui.mvc.dto.EditConfigurationDto;
import com.deswaef.wowscrappie.ui.weakauras.domain.WeakAura;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;

import java.util.List;
import java.util.Optional;

public interface WeakAuraService extends OnRoleDependable {

    WeakAura createWeakAura(WeakAura weakAura);

    List<WeakAura> findByWowClass(WowClass wowClass);

    List<WeakAura> findBySpec(Spec spec);

    List<WeakAura> findByBoss(Boss boss);

    Long countByWowClass(WowClass wowClass);

    Long countBySpec(Spec spec);

    Long countByBoss(Boss boss);

    Optional<WeakAura> byId(Long id);

    long count();

    void saveScreenshot(String reference, WeakAura weakAura);

    List<Screenshot> findScreenshots(WeakAura weakAura);

    List<WeakAura> findAll();

    void approve(Long id);

    void delete(Long id);

    void disable(Long id);

    List<WeakAura> findAllFromUser(ScrappieUser scrappieUser);

    List<WeakAura> findAllNonApproved();

    long countAllFromUser(ScrappieUser scrappieUser);

    long countUnapproved();

    void edit(EditConfigurationDto dto);
}
