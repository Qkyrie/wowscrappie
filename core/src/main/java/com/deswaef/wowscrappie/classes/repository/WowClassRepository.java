package com.deswaef.wowscrappie.classes.repository;

import com.deswaef.wowscrappie.classes.domain.WowClass;
import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WowClassRepository extends JpaRepository<WowClass, Long> {

    Optional<WowClass> findBySlug(@Param("slug") String slug);

}
