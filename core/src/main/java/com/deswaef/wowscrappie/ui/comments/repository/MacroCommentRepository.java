package com.deswaef.wowscrappie.ui.comments.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.ui.comments.domain.MacroComment;
import com.deswaef.wowscrappie.ui.macros.domain.Macro;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MacroCommentRepository extends JpaRepository<MacroComment, Long> {
    List<MacroComment> findByMacro(@Param("macro") Macro macro);
}
