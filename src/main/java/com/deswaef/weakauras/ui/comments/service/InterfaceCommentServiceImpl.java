package com.deswaef.weakauras.ui.comments.service;

import com.deswaef.weakauras.notifications.controller.dto.PersistentNotificationDto;
import com.deswaef.weakauras.notifications.service.PersistentNotificationService;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private InterfaceCommentRepository interfaceCommentRepository;
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
    public void postComment(PostCommentDto commentDto, WeakAura weakAura) {
        try {
            Optional<ScrappieUser> currentUser = getCurrentUser();
            if (currentUser.isPresent()) {
                interfaceCommentRepository.save(
                        new WeakAuraComment()
                                .setWeakAura(weakAura)
                                .setComment(commentDto.getComment())
                                .setPostDate(now())
                                .setCommenter(currentUser.get())
                );

                if (!currentUser.equals(weakAura.getUploader())) {
                    persistentNotificationService.createPersistentNotification(
                            weakAura.getUploader(),
                            PersistentNotificationDto.create()
                                    .setTitle(String.format("%s commented on %s", currentUser.get().getUsername(), weakAura.getName()))
                                    .setContent(commentDto.getComment())
                                    .setUrl(String.format("/shared/wa/%s", weakAura.getId())
                                    ));
                }
            } else {
                throw new IllegalArgumentException("Unable to post the comment, please try again later!");
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("Unable to post the comment, please try again later!");
        }
    }

    @Override
    @Transactional
    public void postComment(PostCommentDto commentDto, TellMeWhen tellMeWhen) {
        try {
            Optional<ScrappieUser> currentUser = getCurrentUser();
            if (currentUser.isPresent()) {
                interfaceCommentRepository.save(
                        new TellMeWhenComment()
                                .setTellMeWhen(tellMeWhen)
                                .setComment(commentDto.getComment())
                                .setPostDate(now())
                                .setCommenter(currentUser.get())
                );

                if (!currentUser.equals(tellMeWhen.getUploader())) {
                    persistentNotificationService.createPersistentNotification(
                            tellMeWhen.getUploader(),
                            PersistentNotificationDto.create()
                                    .setTitle(String.format("%s commented on %s", currentUser.get().getUsername(), tellMeWhen.getName()))
                                    .setContent(commentDto.getComment())
                                    .setUrl(String.format("/shared/tmw/%s", tellMeWhen.getId()))
                    );
                }
            } else {
                throw new IllegalArgumentException("Unable to post the comment, please try again later!");
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("Unable to post the comment, please try again later!");
        }
    }

    @Override
    @Transactional
    public void postComment(PostCommentDto commentDto, Macro macro) {
        try {
            Optional<ScrappieUser> currentUser = getCurrentUser();
            if (currentUser.isPresent()) {
                interfaceCommentRepository.save(
                        new MacroComment()
                                .setMacro(macro)
                                .setComment(commentDto.getComment())
                                .setPostDate(now())
                                .setCommenter(currentUser.get())
                );

                if (!currentUser.equals(macro.getUploader())) {
                    persistentNotificationService.createPersistentNotification(
                            macro.getUploader(),
                            PersistentNotificationDto.create()
                                    .setTitle(String.format("%s commented on %s", currentUser.get().getUsername(), macro.getName()))
                                    .setContent(commentDto.getComment())
                                    .setUrl(String.format("/shared/macro/%s", macro.getId()))
                    );
                }
            } else {
                throw new IllegalArgumentException("Unable to post the comment, please try again later!");
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("Unable to post the comment, please try again later!");
        }
    }

    private Date now() {
        return new Date();
    }

    private Optional<ScrappieUser> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof ScrappieUser) {
            return Optional.of((ScrappieUser) authentication.getPrincipal());
        } else {
            return Optional.empty();
        }
    }
}
