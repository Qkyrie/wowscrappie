package com.deswaef.weakauras.ui.weakauras.service;

import com.deswaef.weakauras.classes.domain.Spec;
import com.deswaef.weakauras.classes.domain.WowClass;
import com.deswaef.weakauras.infrastructure.service.OnRoleDependable;
import com.deswaef.weakauras.ui.image.domain.Screenshot;
import com.deswaef.weakauras.ui.macros.domain.Macro;
import com.deswaef.weakauras.ui.mvc.dto.EditConfigurationDto;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.transaction.annotation.Transactional;

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

    List<WeakAura> findAllFromUser(ScrappieUser scrappieUser);

    List<WeakAura> findAllNonApproved();

    long countAllFromUser(ScrappieUser scrappieUser);

    long countUnapproved();

    void edit(EditConfigurationDto dto);
}
