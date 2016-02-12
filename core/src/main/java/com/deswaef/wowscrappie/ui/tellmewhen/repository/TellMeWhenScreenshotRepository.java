package com.deswaef.wowscrappie.ui.tellmewhen.repository;


import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.ui.image.domain.Screenshot;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.TellMeWhenScreenshot;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TellMeWhenScreenshotRepository extends JpaRepository<TellMeWhenScreenshot, Long>{
    List<Screenshot> findByTellMeWhen(@Param("tellMeWhen") TellMeWhen tellMeWhen);
}
