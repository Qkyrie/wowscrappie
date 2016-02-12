package com.deswaef.wowscrappie.ui.weakauras.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.ui.image.domain.Screenshot;
import com.deswaef.wowscrappie.ui.weakauras.domain.WeakAura;
import com.deswaef.wowscrappie.ui.weakauras.domain.WeakauraScreenshot;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeakAuraScreenshotRepository extends JpaRepository<WeakauraScreenshot, Long>{
    List<Screenshot> findByWeakAura(@Param("weakAura") WeakAura weakAura);
}
