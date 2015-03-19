package com.deswaef.weakauras.ui.weakauras.repository;

import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeakAuraRepository extends JpaRepository<WeakAura, Long>{
    @Query("select count(wa) from WeakAura wa where wa.approved = :approved")
    long countApproved(@Param("approved") boolean approved);

    @Query("select wa from WeakAura wa where wa.approved = :approved")
    List<WeakAura> findAllApproved(@Param("approved") boolean approved);

    List<WeakAura> findByUploader(@Param("uploader") ScrappieUser scrappieUser);

    @Query("select count(wa) from WeakAura wa where wa.uploader = :uploader")
    long countByUploader(@Param("uploader") ScrappieUser scrappieUser);
}
