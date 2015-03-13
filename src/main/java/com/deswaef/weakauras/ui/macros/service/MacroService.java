package com.deswaef.weakauras.ui.macros.service;

import com.deswaef.weakauras.classes.domain.Spec;
import com.deswaef.weakauras.classes.domain.WowClass;
import com.deswaef.weakauras.infrastructure.service.OnRoleDependable;
import com.deswaef.weakauras.ui.macros.domain.Macro;

import java.util.List;
import java.util.Optional;

public interface MacroService extends OnRoleDependable {
    Macro newMacro(Macro macro);

    List<Macro> findByWowClass(WowClass wowClass);

    List<Macro> findBySpec(Spec spec);

    Long countByWowClass(WowClass wowClass);

    Long countBySpec(Spec spec);

    Optional<Macro> byId(Long id);

    long count();

    List<Macro> findAll();

    void approve(Long id);

    void delete(Long macro);

    void disable(Long macro);
}
