package com.deswaef.weakauras.ui.image.repository;

import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.ui.image.domain.Screenshot;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ScreenshotRepository extends JpaRepository<Screenshot, Long>{
    Optional<Screenshot> findByReference(@Param("reference") String reference);
}
