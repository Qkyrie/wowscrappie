package com.deswaef.weakauras.ui.macros.repository;

import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.ui.macros.domain.Macro;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MacroRepository extends JpaRepository<Macro, Long>{
    @Query("select count(m) from Macro m where m.approved = true")
    long countApproved();

    @Query("select m from Macro m where m.approved = true")
    List<Macro> findAllApproved();

    List<Macro> findByUploader(@Param("uploader") ScrappieUser scrappieUser);

    @Query("select count(m) from Macro m where m.uploader = :uploader")
    long countByUploader(@Param("uploader") ScrappieUser scrappieUser);

}
