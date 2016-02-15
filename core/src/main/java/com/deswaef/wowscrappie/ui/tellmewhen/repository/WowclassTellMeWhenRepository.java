package com.deswaef.wowscrappie.ui.tellmewhen.repository;

import com.deswaef.wowscrappie.classes.domain.WowClass;
import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.WowClassTellMeWhen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WowclassTellMeWhenRepository extends JpaRepository<WowClassTellMeWhen, Long> {
    List<TellMeWhen> findByWowClass(@Param("wowClass") WowClass wowClass);

    @Query("select tmw from WowClassTellMeWhen tmw where wowClass = :wowClass and approved = true")
    List<TellMeWhen> findByWowClassAndApproved(@Param("wowClass") WowClass wowClass);

    @Query("select count(tmw) from WowClassTellMeWhen tmw where wowClass = :wowClass")
    Long countByWowClass(@Param("wowClass") WowClass wowClass);

    @Query("select count(tmw) from WowClassTellMeWhen tmw where wowClass = :wowClass and approved = true")
    Long countByWowClassAndApproved(@Param("wowClass") WowClass wowClass);
}
