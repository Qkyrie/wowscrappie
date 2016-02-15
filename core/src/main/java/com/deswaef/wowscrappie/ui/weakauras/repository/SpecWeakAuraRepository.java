package com.deswaef.wowscrappie.ui.weakauras.repository;

import com.deswaef.wowscrappie.classes.domain.Spec;
import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.ui.weakauras.domain.SpecWeakAura;
import com.deswaef.wowscrappie.ui.weakauras.domain.WeakAura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecWeakAuraRepository extends JpaRepository<SpecWeakAura, Long> {
    List<WeakAura> findBySpec(@Param("spec") Spec spec);

    @Query("select wa from SpecWeakAura wa where wa.spec = :spec and approved = true")
    List<WeakAura> findBySpecAndApproved(@Param("spec") Spec spec);

    @Query("select count(wa) from SpecWeakAura wa where wa.spec = :spec and approved = true")
    Long countBySpecAndApproved(@Param("spec") Spec spec);

    @Query("select count(wa) from SpecWeakAura wa where wa.spec = :spec")
    Long countBySpec(@Param("spec") Spec spec);
}
