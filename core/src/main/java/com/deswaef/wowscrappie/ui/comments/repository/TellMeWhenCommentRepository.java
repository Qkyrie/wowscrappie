package com.deswaef.wowscrappie.ui.comments.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.ui.comments.domain.TellMeWhenComment;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.TellMeWhen;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TellMeWhenCommentRepository extends JpaRepository<TellMeWhenComment, Long> {

    List<TellMeWhenComment> findByTellMeWhen(@Param("tellMeWhen") TellMeWhen tellMeWhen);

}
