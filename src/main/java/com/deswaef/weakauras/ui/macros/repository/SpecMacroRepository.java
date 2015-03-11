package com.deswaef.weakauras.ui.macros.repository;

import com.deswaef.weakauras.classes.domain.Spec;
import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.ui.macros.domain.Macro;
import com.deswaef.weakauras.ui.macros.domain.SpecMacro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecMacroRepository extends JpaRepository<SpecMacro, Long> {
    List<Macro> findBySpec(@Param("spec") Spec spec);

    @Query("select sm from SpecMacro sm where spec = :spec and approved = true ")
    List<Macro> findBySpecAndApproved(@Param("spec") Spec spec);
}
