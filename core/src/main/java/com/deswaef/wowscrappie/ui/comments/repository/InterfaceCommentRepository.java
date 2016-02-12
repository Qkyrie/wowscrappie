package com.deswaef.wowscrappie.ui.comments.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.ui.comments.domain.InterfaceComment;

public interface InterfaceCommentRepository extends JpaRepository<InterfaceComment, Long>{
}
