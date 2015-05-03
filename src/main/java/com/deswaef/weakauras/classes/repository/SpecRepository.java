package com.deswaef.weakauras.classes.repository;

import com.deswaef.weakauras.classes.domain.Spec;
import com.deswaef.weakauras.classes.domain.WowClass;
import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SpecRepository extends JpaRepository<Spec, Long>{
    Optional<Spec> findBySlugAndWowClass(@Param("slug") String slug, @Param("wowClass")WowClass wowClass);

    @Query("select s from Spec s where s.warcraftlogsId = :warcraftlogsId and s.wowClass.warcraftlogsId = :wowclassWclId")
    Optional<Spec> findByWarcraftlogsIdAndWowClassWarcraftlogsId(@Param("warcraftlogsId") Long warcraftlogsId, @Param("wowclassWclId") Long wowclassWarcraftlogsId);
}
