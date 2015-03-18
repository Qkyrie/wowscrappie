package com.deswaef.weakauras.ui.tellmewhen.repository;

import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.ui.image.domain.Screenshot;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TellMeWhenRepository extends JpaRepository<TellMeWhen, Long>{
    @Query("select count(tmw) from TellMeWhen tmw where approved = true")
    long countApproved();

    @Query("select tmw from TellMeWhen tmw where approved = true")
    List<TellMeWhen> findAllApproved();

    List<TellMeWhen> findByUploader(@Param("uploader") ScrappieUser scrappieUser);

    @Query("select count(tmw) from TellMeWhen tmw where tmw.uploader = :uploader")
    long countByUploader(@Param("uploader") ScrappieUser scrappieUser);

}
