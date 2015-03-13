package com.deswaef.weakauras.ui.tellmewhen.repository;

import com.deswaef.weakauras.classes.domain.Spec;
import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.ui.tellmewhen.domain.SpecTellMeWhen;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecTellMeWhenRepository extends JpaRepository<SpecTellMeWhen, Long>{
    List<TellMeWhen> findBySpec(@Param("spec") Spec spec);

    @Query("select tmw from SpecTellMeWhen tmw where tmw.spec = :spec and approved = true")
    List<TellMeWhen> findBySpecAndApproved(@Param("spec") Spec spec);

    @Query("select count(tmw) from SpecTellMeWhen tmw where tmw.spec = :spec")
    Long countBySpec(@Param("spec") Spec spec);

    @Query("select count(tmw) from SpecTellMeWhen tmw where tmw.spec = :spec and approved = true")
    Long countBySpecAndApproved(@Param("spec") Spec spec);
}
