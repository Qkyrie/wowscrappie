package com.deswaef.weakauras.classes.repository;

import com.deswaef.weakauras.classes.domain.WowClass;
import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WowClassRepository extends JpaRepository<WowClass, Long>{

    Optional<WowClass> findBySlug(@Param("slug") String slug);

}
