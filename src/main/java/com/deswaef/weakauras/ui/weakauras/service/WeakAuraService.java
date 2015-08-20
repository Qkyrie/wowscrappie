package com.deswaef.weakauras.ui.weakauras.service;

import com.deswaef.weakauras.classes.domain.Spec;
import com.deswaef.weakauras.classes.domain.WowClass;
import com.deswaef.weakauras.infrastructure.service.OnRoleDependable;
import com.deswaef.weakauras.raids.domain.Boss;
import com.deswaef.weakauras.ui.image.domain.Screenshot;
import com.deswaef.weakauras.ui.mvc.dto.EditConfigurationDto;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;

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
