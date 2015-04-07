package com.deswaef.weakauras.ui.comments.controller;

import com.deswaef.weakauras.messaging.domain.PrivateMessage;
import com.deswaef.weakauras.ui.comments.controller.dto.CommentListDto;
import com.deswaef.weakauras.ui.comments.controller.dto.PostCommentDto;
import com.deswaef.weakauras.ui.comments.domain.InterfaceComment;
import com.deswaef.weakauras.ui.comments.service.InterfaceCommentService;
import com.deswaef.weakauras.ui.macros.domain.Macro;
import com.deswaef.weakauras.ui.macros.service.MacroService;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.weakauras.ui.tellmewhen.service.TellMeWhenService;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
import com.deswaef.weakauras.ui.weakauras.service.WeakAuraService;
import com.google.common.base.Strings;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/interface/comments")
public class InterfaceCommentController {

    public static final String TMW = "tmw";
    public static final String WA = "wa";
    public static final String MACRO = "macro";

    private static final Comparator<InterfaceComment> byDate = (pm1, pm2) -> pm1.getPostDate().compareTo(pm2.getPostDate());
    private static final PrettyTime prettyTime = new PrettyTime(Locale.ENGLISH);


    @Autowired
    private InterfaceCommentService interfaceCommentService;
    @Autowired
    private MacroService macroService;
    @Autowired
    private TellMeWhenService tellMeWhenService;
    @Autowired
    private WeakAuraService weakAuraService;

    @RequestMapping(method = POST)
    public String doComment(ModelMap map, @RequestBody PostCommentDto postCommentDto) {
        if(!Strings.isNullOrEmpty(postCommentDto.getComment())) {
            if (TMW.equalsIgnoreCase(postCommentDto.getType())) {
                Optional<TellMeWhen> tmw = getTMW(postCommentDto.getInterfaceId());
                if (tmw.isPresent()) {
                    try {
                        interfaceCommentService.postComment(postCommentDto, tmw.get());
                    } catch (Exception ex) {
                        map.put("post_failure", ex.getMessage());
                    }
                    map.put("comments", interfaceCommentService.findComments(tmw.get())
                            .stream()
                            .sorted(byDate)
                            .map(comment -> new CommentListDto()
                                    .setWhen(prettyTime.format(comment.getPostDate()))
                                    .setCommenter(comment.getCommenter().getUsername())
                                    .setId(comment.getId())
                                    .setContent(comment.getComment())).collect(Collectors.toList()));
                } else {
                    map.put("comments", new ArrayList<>());
                }
            } else if (WA.equalsIgnoreCase(postCommentDto.getType())) {
                Optional<WeakAura> wa = getWA(postCommentDto.getInterfaceId());
                if (wa.isPresent()) {
                    try {
                        interfaceCommentService.postComment(postCommentDto, wa.get());
                    } catch (Exception ex) {
                        map.put("post_failure", ex.getMessage());
                    }
                    map.put("comments", interfaceCommentService.findComments(wa.get()).stream()
                            .sorted(byDate)
                            .map(comment -> new CommentListDto()
                                    .setWhen(prettyTime.format(comment.getPostDate()))
                                    .setCommenter(comment.getCommenter().getUsername())
                                    .setId(comment.getId())
                                    .setContent(comment.getComment())).collect(Collectors.toList()));
                } else {
                    map.put("comments", new ArrayList<>());
                }
            } else if (MACRO.equalsIgnoreCase(postCommentDto.getType())) {
                Optional<Macro> macro = getMacro(postCommentDto.getInterfaceId());
                if (macro.isPresent()) {
                    try {
                        interfaceCommentService.postComment(postCommentDto, macro.get());
                    } catch (Exception ex) {
                        map.put("post_failure", ex.getMessage());
                    }
                    map.put("comments", interfaceCommentService.findComments(macro.get()).stream()
                            .sorted(byDate)
                            .map(comment -> new CommentListDto()
                                    .setWhen(prettyTime.format(comment.getPostDate()))
                                    .setCommenter(comment.getCommenter().getUsername())
                                    .setId(comment.getId())
                                    .setContent(comment.getComment())).collect(Collectors.toList()));
                } else {
                    map.put("comments", new ArrayList<>());
                }
            } else {
                map.put("comments", new ArrayList<>());
            }
        } else {
            map.put("post_failure", "Please fill in an actual comment before posting");
        }

        map.put("interfaceType", postCommentDto.getType());
        map.put("interfaceId", postCommentDto.getInterfaceId());
        return "comments/comments :: showComments";
    }

    @RequestMapping(value = "/{type}/{id}", method = RequestMethod.GET)
    public String findById(ModelMap map, @PathVariable("type") String type, @PathVariable("id") Long id) {
        if (TMW.equalsIgnoreCase(type)) {
            Optional<TellMeWhen> tmw = getTMW(id);
            if (tmw.isPresent()) {
                map.put("comments", interfaceCommentService.findComments(tmw.get()).stream()
                        .sorted(byDate)
                        .map(comment -> new CommentListDto()
                                .setWhen(prettyTime.format(comment.getPostDate()))
                                .setCommenter(comment.getCommenter().getUsername())
                                .setId(comment.getId())
                                .setContent(comment.getComment())).collect(Collectors.toList()));
            } else {
                map.put("comments", new ArrayList<>());
            }
        } else if (WA.equalsIgnoreCase(type)) {
            Optional<WeakAura> wa = getWA(id);
            if (wa.isPresent()) {
                map.put("comments", interfaceCommentService.findComments(wa.get())
                        .stream()
                        .sorted(byDate)
                        .map(comment -> new CommentListDto()
                                .setWhen(prettyTime.format(comment.getPostDate()))
                                .setCommenter(comment.getCommenter().getUsername())
                                .setId(comment.getId())
                                .setContent(comment.getComment())).collect(Collectors.toList()));
            } else {
                map.put("comments", new ArrayList<>());
            }
        } else if (MACRO.equalsIgnoreCase(type)) {
            Optional<Macro> macro = getMacro(id);
            if (macro.isPresent()) {
                map.put("comments", interfaceCommentService.findComments(macro.get()).stream()
                        .sorted(byDate)
                        .map(comment -> new CommentListDto()
                                .setWhen(prettyTime.format(comment.getPostDate()))
                                .setCommenter(comment.getCommenter().getUsername())
                                .setId(comment.getId())
                                .setContent(comment.getComment())).collect(Collectors.toList()));
            } else {
                map.put("comments", new ArrayList<>());
            }
        } else {
            map.put("comments", new ArrayList<>());
        }

        map.put("interfaceType", type);
        map.put("interfaceId", id);
        return "comments/comments :: showComments";
    }

    private Optional<Macro> getMacro(Long id) {
        return macroService.byId(id);
    }

    private Optional<WeakAura> getWA(Long id) {
        return weakAuraService.byId(id);
    }

    private Optional<TellMeWhen> getTMW(Long id) {
        return tellMeWhenService.findById(id);
    }
}
