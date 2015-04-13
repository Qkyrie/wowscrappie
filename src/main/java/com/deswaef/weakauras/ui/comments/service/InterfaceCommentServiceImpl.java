package com.deswaef.weakauras.ui.comments.service;

import com.deswaef.weakauras.notifications.controller.dto.PersistentNotificationDto;
import com.deswaef.weakauras.notifications.service.PersistentNotificationService;
import com.deswaef.weakauras.security.SecurityUtility;
import com.deswaef.weakauras.ui.comments.controller.dto.PostCommentDto;
import com.deswaef.weakauras.ui.comments.domain.MacroComment;
import com.deswaef.weakauras.ui.comments.domain.TellMeWhenComment;
import com.deswaef.weakauras.ui.comments.domain.WeakAuraComment;
import com.deswaef.weakauras.ui.comments.repository.InterfaceCommentRepository;
import com.deswaef.weakauras.ui.comments.repository.MacroCommentRepository;
import com.deswaef.weakauras.ui.comments.repository.TellMeWhenCommentRepository;
import com.deswaef.weakauras.ui.comments.repository.WeakauraCommentRepository;
import com.deswaef.weakauras.ui.macros.domain.Macro;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InterfaceCommentServiceImpl implements InterfaceCommentService {

    @Autowired
    private MacroCommentRepository macroCommentRepository;
    @Autowired
    private WeakauraCommentRepository weakauraCommentRepository;
    @Autowired
    private TellMeWhenCommentRepository tellMeWhenCommentRepository;
    @Autowired
    private PersistentNotificationService persistentNotificationService;

    @Override
    @Transactional(readOnly = true)
    public List<WeakAuraComment> findComments(WeakAura weakAura) {
        return weakauraCommentRepository.findByWeakAura(weakAura);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MacroComment> findComments(Macro macro) {
        return macroCommentRepository.findByMacro(macro);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TellMeWhenComment> findComments(TellMeWhen tellMeWhen) {
        return tellMeWhenCommentRepository.findByTellMeWhen(tellMeWhen);
    }

    @Override
    @Transactional
    public void notifyForComment(WeakAura weakAura) {
        persistentNotificationService.createPersistentNotification(
                weakAura.getUploader(),
                PersistentNotificationDto.create()
                        .setTitle(String.format("Someone commented on %s", weakAura.getName()))
                        .setContent(String.format("Someone commented on %s", weakAura.getName()))
                        .setUrl(String.format("/shared/wa/%s", weakAura.getId())));
    }

    @Override
    @Transactional
    public void notifyForComment(TellMeWhen tellMeWhen) {
        persistentNotificationService.createPersistentNotification(
                tellMeWhen.getUploader(),
                PersistentNotificationDto.create()
                        .setTitle(String.format("Someone commented on %s", tellMeWhen.getName()))
                        .setContent(String.format("Someone commented on %s", tellMeWhen.getName()))
                        .setUrl(String.format("/shared/tmw/%s", tellMeWhen.getId()))
        );
    }

    @Override
    @Transactional
    public void notifyForComment(Macro macro) {
        persistentNotificationService.createPersistentNotification(
                macro.getUploader(),
                PersistentNotificationDto.create()
                        .setTitle(String.format("Someone commented on %s", macro.getName()))
                        .setContent(String.format("Someone commented on %s", macro.getName()))
                        .setUrl(String.format("/shared/macro/%s", macro.getId()))
        );
    }
}
