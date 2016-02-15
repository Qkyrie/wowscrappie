package com.deswaef.wowscrappie.ui.reports.service;

import com.deswaef.wowscrappie.ui.macros.domain.Macro;
import com.deswaef.wowscrappie.ui.macros.repository.MacroRepository;
import com.deswaef.wowscrappie.ui.reports.domain.InterfaceReport;
import com.deswaef.wowscrappie.ui.reports.domain.MacroReport;
import com.deswaef.wowscrappie.ui.reports.domain.TellMeWhenReport;
import com.deswaef.wowscrappie.ui.reports.domain.WeakAuraReport;
import com.deswaef.wowscrappie.ui.reports.repository.InterfaceReportRepository;
import com.deswaef.wowscrappie.ui.reports.repository.MacroReportRepository;
import com.deswaef.wowscrappie.ui.reports.repository.TellMeWhenReportRepository;
import com.deswaef.wowscrappie.ui.reports.repository.WeakAuraReportRepository;
import com.deswaef.wowscrappie.ui.reports.service.dto.CreateInterfaceReportDto;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.wowscrappie.ui.tellmewhen.repository.TellMeWhenRepository;
import com.deswaef.wowscrappie.ui.weakauras.domain.WeakAura;
import com.deswaef.wowscrappie.ui.weakauras.repository.WeakAuraRepository;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
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
    public void createReport(CreateInterfaceReportDto createInterfaceReportDto) {
        String interfaceType = createInterfaceReportDto.getInterfaceType();
        Long interfaceId = createInterfaceReportDto.getInterfaceId();
        String comment = createInterfaceReportDto.getComment();

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

    @Override
    @Transactional(readOnly = true)
    public List<InterfaceReport> findAll() {
        return interfaceReportRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        interfaceReportRepository.findOne(id).ifPresent(interfaceReportRepository::delete);
    }

    @Override
    @Transactional
    public void setHandled(Long id) {
        interfaceReportRepository.findOne(id)
                .ifPresent(
                        one -> interfaceReportRepository.save(
                                one.setHandled(true)
                        )
                );
    }

    @Override
    @Transactional
    public InterfaceReport findOne(Long id) {
        Optional<InterfaceReport> one = interfaceReportRepository.findOne(id);
        if (one.isPresent()) {
            return one.get();
        } else {
            throw new IllegalArgumentException("a report with that id was not found");
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
        interfaceReportRepository.save(
                new InterfaceReport()
                        .setPostDate(now())
                        .setComment(report)
                        .setHandled(false)
        );
    }
}
