package com.deswaef.wowscrappie.ui.tellmewhen.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TellMeWhenRepository extends JpaRepository<TellMeWhen, Long>{
    @Query("select count(tmw) from TellMeWhen tmw where approved = :approved")
    long countApproved(@Param("approved") boolean approved);

    @Query("select tmw from TellMeWhen tmw where approved = :approved")
    List<TellMeWhen> findAllApproved(@Param("approved") boolean approved);

    List<TellMeWhen> findByUploader(@Param("uploader") ScrappieUser scrappieUser);

    @Query("select count(tmw) from TellMeWhen tmw where tmw.uploader = :uploader")
    long countByUploader(@Param("uploader") ScrappieUser scrappieUser);

}
