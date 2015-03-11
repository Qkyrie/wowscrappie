package com.deswaef.weakauras.ui.weakauras.repository;

import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WeakAuraRepository extends JpaRepository<WeakAura, Long>{
    @Query("select count(wa) from WeakAura wa where wa.approved = true")
    long countApproved();

    @Query("select wa from WeakAura wa where wa.approved = true")
    List<WeakAura> findAllApproved();
}
