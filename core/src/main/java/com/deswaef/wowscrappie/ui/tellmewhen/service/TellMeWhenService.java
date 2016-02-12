package com.deswaef.wowscrappie.ui.tellmewhen.service;

import com.deswaef.wowscrappie.classes.domain.Spec;
import com.deswaef.wowscrappie.classes.domain.WowClass;
import com.deswaef.wowscrappie.infrastructure.service.OnRoleDependable;
import com.deswaef.wowscrappie.raids.domain.Boss;
import com.deswaef.wowscrappie.ui.dto.EditConfigurationDto;
import com.deswaef.wowscrappie.ui.image.domain.Screenshot;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;

import java.util.List;
import java.util.Optional;

public interface TellMeWhenService extends OnRoleDependable {
    TellMeWhen createTMW(TellMeWhen tellMeWhen);

    List<TellMeWhen> findByWowclass(WowClass wowClass);

    List<TellMeWhen> findBySpec(Spec spec);

    List<TellMeWhen> findByBoss(Boss boss);

    Long countByWowclass(WowClass wowClass);

    Long countBySpec(Spec spec);

    Long countByBoss(Boss boss);

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
