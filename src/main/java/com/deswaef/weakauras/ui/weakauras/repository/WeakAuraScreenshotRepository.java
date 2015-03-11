package com.deswaef.weakauras.ui.weakauras.repository;

import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.ui.image.domain.Screenshot;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
import com.deswaef.weakauras.ui.weakauras.domain.WeakauraScreenshot;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeakAuraScreenshotRepository extends JpaRepository<WeakauraScreenshot, Long>{
    List<Screenshot> findByWeakAura(@Param("weakAura") WeakAura weakAura);
}
