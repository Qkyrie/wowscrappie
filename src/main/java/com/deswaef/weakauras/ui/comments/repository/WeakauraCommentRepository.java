package com.deswaef.weakauras.ui.comments.repository;

import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.ui.comments.domain.WeakAuraComment;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeakauraCommentRepository extends JpaRepository<WeakAuraComment, Long>{

    List<WeakAuraComment> findByWeakAura(@Param("weakAura") WeakAura weakAura);

}
