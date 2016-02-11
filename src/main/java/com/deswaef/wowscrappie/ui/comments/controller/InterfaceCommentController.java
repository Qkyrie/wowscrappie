package com.deswaef.wowscrappie.ui.comments.controller;

import com.deswaef.wowscrappie.ui.comments.controller.dto.CommentListDto;
import com.deswaef.wowscrappie.ui.comments.controller.dto.DisqusCommentDto;
import com.deswaef.wowscrappie.ui.comments.domain.InterfaceComment;
import com.deswaef.wowscrappie.ui.comments.service.InterfaceCommentService;
import com.deswaef.wowscrappie.ui.macros.domain.Macro;
import com.deswaef.wowscrappie.ui.macros.service.MacroService;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.wowscrappie.ui.tellmewhen.service.TellMeWhenService;
import com.deswaef.wowscrappie.ui.weakauras.domain.WeakAura;
import com.deswaef.wowscrappie.ui.weakauras.service.WeakAuraService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/interface/comments")
public class InterfaceCommentController {

    public static final String TMW = "tmw";
    public static final String WA = "wa";
    public static final String MACRO = "macro";

    private static final Comparator<InterfaceComment> byDate = (pm1, pm2) -> pm1.getPostDate().compareTo(pm2.getPostDate());
    private static final PrettyTime prettyTime = new PrettyTime(Locale.ENGLISH);

    private Log logger = LogFactory.getLog(this.getClass());


    @Autowired
    private InterfaceCommentService interfaceCommentService;
    @Autowired
    private MacroService macroService;
    @Autowired
    private TellMeWhenService tellMeWhenService;
    @Autowired
    private WeakAuraService weakAuraService;

    @RequestMapping(value = "/{type}/{id}/disqus", method = RequestMethod.GET)
    public String findByIdDisqus(ModelMap map, @PathVariable("type") String type, @PathVariable("id") long id) {
        map.put("configType", type);
        map.put("configId", id);
        map.put("interfaceTitle", getTitle(type, id));
        return "comments/comments :: disqusComments";
    }

    private String getTitle(String type, long id) {
        if (TMW.equalsIgnoreCase(type)) {
            Optional<TellMeWhen> tmw = getTMW(id);
            if (tmw.isPresent()) {
                return String.format("TMW - %s", tmw.get().getName());
            }
        } else if (WA.equalsIgnoreCase(type)) {
            Optional<WeakAura> wa = getWA(id);
            if (wa.isPresent()) {
                return String.format("WA - %s", wa.get().getName());
            }
        } else if (MACRO.equalsIgnoreCase(type)) {
            Optional<Macro> macro = getMacro(id);
            if (macro.isPresent()) {
                return String.format("Macro - %s", macro.get().getName());
            }
        }
        return "";
    }

    @RequestMapping(value = "/{type}/{id}/disqus", method = RequestMethod.POST)
    public
    @ResponseBody
    String newComment(@PathVariable("type") String type, @PathVariable("id") long id, @RequestBody DisqusCommentDto disqusCommentDto) {
        if (TMW.equalsIgnoreCase(type)) {
            Optional<TellMeWhen> tmw = getTMW(id);
            if (tmw.isPresent()) {
                try {
                    interfaceCommentService.notifyForComment(tmw.get());
                } catch (Exception ex) {
                    logger.error(String.format("error during notification of tmwcomment: %s", ex.getMessage()));
                }
            }
        } else if (WA.equalsIgnoreCase(type)) {
            Optional<WeakAura> wa = getWA(id);
            if (wa.isPresent()) {
                try {
                    interfaceCommentService.notifyForComment(wa.get());
                } catch (Exception ex) {
                    logger.error(String.format("error during notification of wacomment: %s", ex.getMessage()));
                }
            }
        } else if (MACRO.equalsIgnoreCase(type)) {
            Optional<Macro> macro = getMacro(id);
            if (macro.isPresent()) {
                try {
                    interfaceCommentService.notifyForComment(macro.get());
                } catch (Exception ex) {
                    logger.error(String.format("error during notification of macrocomment: %s", ex.getMessage()));
                }
            }
        }
        return "OK";
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
