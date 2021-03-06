package com.deswaef.wowscrappie.ui.macros.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.ui.macros.domain.Macro;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MacroRepository extends JpaRepository<Macro, Long> {
    @Query("select count(m) from Macro m where m.approved = :approved")
    long countApproved(@Param("approved") boolean approved);

    @Query("select m from Macro m where m.approved = :approved")
    List<Macro> findAllApproved(@Param("approved") boolean approved);

    List<Macro> findByUploader(@Param("uploader") ScrappieUser scrappieUser);

    @Query("select count(m) from Macro m where m.uploader = :uploader")
    long countByUploader(@Param("uploader") ScrappieUser scrappieUser);

}
