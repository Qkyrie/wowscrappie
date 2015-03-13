package com.deswaef.weakauras.ui.weakauras.repository;

import com.deswaef.weakauras.classes.domain.WowClass;
import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
import com.deswaef.weakauras.ui.weakauras.domain.WowClassWeakAura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WowclassWeakAuraRepository extends JpaRepository<WowClassWeakAura, Long>{
    List<WeakAura> findByWowClass(@Param("wowClass") WowClass wowClass);

    @Query("select wa from WowClassWeakAura wa where wa.wowClass = :wowClass and approved = true")
    List<WeakAura> findByWowClassAndApproved(@Param("wowClass") WowClass wowClass);

    @Query("select count(wa) from WowClassWeakAura wa where wa.wowClass = :wowClass and approved = true")
    Long countByWowClassAndApproved(@Param("wowClass") WowClass wowClass);

    @Query("select count(wa) from WowClassWeakAura wa where wa.wowClass = :wowClass")
    Long countByWowClass(@Param("wowClass") WowClass wowClass);
}
