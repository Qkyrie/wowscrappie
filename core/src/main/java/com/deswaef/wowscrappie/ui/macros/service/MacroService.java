package com.deswaef.wowscrappie.ui.macros.service;

import com.deswaef.wowscrappie.classes.domain.Spec;
import com.deswaef.wowscrappie.classes.domain.WowClass;
import com.deswaef.wowscrappie.infrastructure.service.OnRoleDependable;
import com.deswaef.wowscrappie.raids.domain.Boss;
import com.deswaef.wowscrappie.ui.dto.EditConfigurationDto;
import com.deswaef.wowscrappie.ui.macros.domain.Macro;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;

import java.util.List;
import java.util.Optional;

public interface MacroService extends OnRoleDependable {
    Macro newMacro(Macro macro);

    List<Macro> findByWowClass(WowClass wowClass);

    List<Macro> findBySpec(Spec spec);

    List<Macro> findByBoss(Boss boss);

    Long countByWowClass(WowClass wowClass);

    Long countBySpec(Spec spec);

    Long countByBoss(Boss boss);

    Optional<Macro> byId(Long id);

    long count();

    List<Macro> findAll();

    void approve(Long id);

    void delete(Long macro);

    void disable(Long macro);

    List<Macro> findAllFromUser(ScrappieUser scrappieUser);

    List<Macro> findAllUnapproved();

    long countAllFromUser(ScrappieUser scrappieUser);

    long countUnapproved();

    void edit(EditConfigurationDto dto);
}
