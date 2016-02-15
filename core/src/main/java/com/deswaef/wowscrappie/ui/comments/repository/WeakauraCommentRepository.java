package com.deswaef.wowscrappie.ui.comments.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.ui.comments.domain.WeakAuraComment;
import com.deswaef.wowscrappie.ui.weakauras.domain.WeakAura;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeakauraCommentRepository extends JpaRepository<WeakAuraComment, Long> {

    List<WeakAuraComment> findByWeakAura(@Param("weakAura") WeakAura weakAura);

}
