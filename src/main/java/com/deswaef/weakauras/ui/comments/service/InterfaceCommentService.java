package com.deswaef.weakauras.ui.comments.service;

import com.deswaef.weakauras.ui.comments.controller.dto.PostCommentDto;
import com.deswaef.weakauras.ui.comments.domain.MacroComment;
import com.deswaef.weakauras.ui.comments.domain.TellMeWhenComment;
import com.deswaef.weakauras.ui.comments.domain.WeakAuraComment;
import com.deswaef.weakauras.ui.macros.domain.Macro;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;

import java.util.List;

public interface InterfaceCommentService {

    List<WeakAuraComment> findComments(WeakAura weakAura);
    List<MacroComment> findComments(Macro macro);
    List<TellMeWhenComment> findComments(TellMeWhen tellMeWhen);

    void postComment(PostCommentDto commentDto, WeakAura weakAura);

    void postComment(PostCommentDto commentDto, TellMeWhen tellMeWhen);

    void postComment(PostCommentDto commentDto, Macro macro);
}
