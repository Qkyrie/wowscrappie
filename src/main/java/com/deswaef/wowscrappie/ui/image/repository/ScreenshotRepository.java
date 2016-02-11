package com.deswaef.wowscrappie.ui.image.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.ui.image.domain.Screenshot;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ScreenshotRepository extends JpaRepository<Screenshot, Long>{
    Optional<Screenshot> findByReference(@Param("reference") String reference);
}
