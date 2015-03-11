package com.deswaef.weakauras.ui.macros.repository;

import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.ui.macros.domain.Macro;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MacroRepository extends JpaRepository<Macro, Long>{
    @Query("select count(m) from Macro m where m.approved = true")
    long countApproved();

    @Query("select m from Macro m where m.approved = true")
    List<Macro> findAllApproved();
}
