package com.deswaef.wowscrappie.ui.comments.service;

import com.deswaef.wowscrappie.ui.comments.domain.MacroComment;
import com.deswaef.wowscrappie.ui.comments.domain.TellMeWhenComment;
import com.deswaef.wowscrappie.ui.comments.domain.WeakAuraComment;
import com.deswaef.wowscrappie.ui.macros.domain.Macro;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.wowscrappie.ui.weakauras.domain.WeakAura;

import java.util.List;

public interface InterfaceCommentService {

    List<WeakAuraComment> findComments(WeakAura weakAura);
    List<MacroComment> findComments(Macro macro);
    List<TellMeWhenComment> findComments(TellMeWhen tellMeWhen);

    void notifyForComment(WeakAura weakAura);
    void notifyForComment(TellMeWhen tellMeWhen);
    void notifyForComment(Macro macro);
}
