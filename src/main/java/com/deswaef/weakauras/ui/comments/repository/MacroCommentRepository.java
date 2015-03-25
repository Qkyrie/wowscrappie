package com.deswaef.weakauras.ui.comments.repository;

import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.ui.comments.domain.MacroComment;
import com.deswaef.weakauras.ui.macros.domain.Macro;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MacroCommentRepository extends JpaRepository<MacroComment, Long>{
    List<MacroComment> findByMacro(@Param("macro") Macro macro);
}
