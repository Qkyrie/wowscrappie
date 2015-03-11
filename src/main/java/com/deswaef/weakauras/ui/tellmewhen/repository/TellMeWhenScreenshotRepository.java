package com.deswaef.weakauras.ui.tellmewhen.repository;


import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.ui.image.domain.Screenshot;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhenScreenshot;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TellMeWhenScreenshotRepository extends JpaRepository<TellMeWhenScreenshot, Long>{
    List<Screenshot> findByTellMeWhen(@Param("tellMeWhen") TellMeWhen tellMeWhen);
}
