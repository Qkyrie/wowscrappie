package com.deswaef.weakauras.ui.comments.repository;

import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.ui.comments.domain.TellMeWhenComment;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TellMeWhenCommentRepository extends JpaRepository<TellMeWhenComment, Long> {

    List<TellMeWhenComment> findByTellMeWhen(@Param("tellMeWhen") TellMeWhen tellMeWhen);

}
