package com.deswaef.weakauras.ui.reports.service;

import com.deswaef.weakauras.ui.macros.domain.Macro;
import com.deswaef.weakauras.ui.macros.repository.MacroRepository;
import com.deswaef.weakauras.ui.reports.controller.dto.InterfaceReportDto;
import com.deswaef.weakauras.ui.reports.domain.MacroReport;
import com.deswaef.weakauras.ui.reports.domain.TellMeWhenReport;
import com.deswaef.weakauras.ui.reports.domain.WeakAuraReport;
import com.deswaef.weakauras.ui.reports.repository.InterfaceReportRepository;
import com.deswaef.weakauras.ui.reports.repository.MacroReportRepository;
import com.deswaef.weakauras.ui.reports.repository.TellMeWhenReportRepository;
import com.deswaef.weakauras.ui.reports.repository.WeakAuraReportRepository;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.weakauras.ui.tellmewhen.repository.TellMeWhenRepository;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
import com.deswaef.weakauras.ui.weakauras.repository.WeakAuraRepository;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class InterfaceReportServiceImpl implements InterfaceReportService {

    public static final String MACRO = "macro";
    public static final String WA = "wa";
    public static final String TMW = "tmw";

    @Autowired
    private WeakAuraRepository weakAuraRepository;
    @Autowired
    private TellMeWhenRepository tellMeWhenRepository;
    @Autowired
    private MacroRepository macroRepository;
    @Autowired
    private TellMeWhenReportRepository tellMeWhenReportRepository;
    @Autowired
    private WeakAuraReportRepository weakAuraReportRepository;
    @Autowired
    private MacroReportRepository macroReportRepository;
    @Autowired
    private InterfaceReportRepository interfaceReportRepository;

    @Override
    @Transactional
    public void createReport(InterfaceReportDto interfaceReportDto) {
        String interfaceType = interfaceReportDto.getInterfaceType();
        Long interfaceId = interfaceReportDto.getInterfaceId();
        String comment = interfaceReportDto.getComment();

        if (interfaceId == null) {
            createGeneralReport(comment);
        } else {
            if (TMW.equalsIgnoreCase(interfaceType)) {
                Optional<TellMeWhen> one = tellMeWhenRepository.findOne(interfaceId);
                if (one.isPresent()) {
                    interfaceReportRepository.save(
                            new TellMeWhenReport()
                                    .setTellMeWhen(one.get())
                                    .setComment(comment)
                                    .setPostDate(now())
                                    .setHandled(false)
                                    .setReporter(getReporter())
                    );
                } else {
                    createGeneralReport(comment);
                }
            } else if (WA.equalsIgnoreCase(interfaceType)) {
                Optional<WeakAura> one = weakAuraRepository.findOne(interfaceId);
                if (one.isPresent()) {
                    interfaceReportRepository.save(
                            new WeakAuraReport()
                                    .setWeakAura(one.get())
                                    .setPostDate(now())
                                    .setComment(comment)
                                    .setHandled(false)
                                    .setReporter(getReporter())
                    );
                } else {
                    createGeneralReport(comment);
                }
            } else if (MACRO.equalsIgnoreCase(interfaceType)) {
                Optional<Macro> one = macroRepository.findOne(interfaceId);
                if (one.isPresent()) {
                    interfaceReportRepository.save(
                            new MacroReport()
                                    .setMacro(one.get())
                                    .setPostDate(now())
                                    .setComment(comment)
                                    .setHandled(false)
                                    .setReporter(getReporter())
                    );
                } else {
                    createGeneralReport(comment);
                }
            } else {
                createGeneralReport(comment);
            }
        }
    }

    private ScrappieUser getReporter() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof ScrappieUser) {
            return (ScrappieUser) authentication.getPrincipal();
        } else {
            return null;
        }
    }

    private Date now() {
        return new Date();
    }

    private void createGeneralReport(String report) {

    }
}
