package com.deswaef.weakauras.ui.tellmewhen.service;

import com.deswaef.weakauras.classes.domain.Spec;
import com.deswaef.weakauras.classes.domain.WowClass;
import com.deswaef.weakauras.infrastructure.service.OnRoleDependable;
import com.deswaef.weakauras.ui.image.domain.Screenshot;
import com.deswaef.weakauras.ui.macros.domain.Macro;
import com.deswaef.weakauras.ui.mvc.dto.EditConfigurationDto;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;

import java.util.List;
import java.util.Optional;

public interface TellMeWhenService extends OnRoleDependable {
    TellMeWhen createTMW(TellMeWhen tellMeWhen);

    List<TellMeWhen> findByWowclass(WowClass wowClass);

    List<TellMeWhen> findBySpec(Spec spec);

    Long countByWowclass(WowClass wowClass);

    Long countBySpec(Spec spec);

    Optional<TellMeWhen> findById(Long id);

    long count();

    void saveScreenshot(String reference, TellMeWhen tellMeWhen);

    List<Screenshot> findScreenshots(TellMeWhen tellMeWhen);

    List<TellMeWhen> findAll();

    void approve(Long id);

    void delete(Long tmw);

    void disable(Long tmw);

    List<TellMeWhen> findAllFromUser(ScrappieUser scrappieUser);

    List<TellMeWhen> findAllNonApproved();

    long countAllFromUser(ScrappieUser scrappieUser);

    long countUnapproved();

    void edit(EditConfigurationDto dto);
}
