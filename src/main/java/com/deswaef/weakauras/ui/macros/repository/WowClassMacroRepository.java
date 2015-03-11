package com.deswaef.weakauras.ui.macros.repository;

import com.deswaef.weakauras.classes.domain.WowClass;
import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.ui.macros.domain.Macro;
import com.deswaef.weakauras.ui.macros.domain.WowClassMacro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WowClassMacroRepository extends JpaRepository<WowClassMacro, Long>{
    List<Macro> findByWowClass(@Param("wowClass") WowClass wowClass);

    @Query("select m from WowClassMacro m where m.wowClass = :wowClass and approved = true")
    List<Macro> findByWowClassAndApproved(@Param("wowClass") WowClass wowClass);

}
