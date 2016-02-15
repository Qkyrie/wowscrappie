package com.deswaef.wowscrappie.ui.reports.controller.dto;

import com.deswaef.wowscrappie.ui.reports.domain.InterfaceReport;
import com.deswaef.wowscrappie.ui.reports.domain.MacroReport;
import com.deswaef.wowscrappie.ui.reports.domain.TellMeWhenReport;
import com.deswaef.wowscrappie.ui.reports.domain.WeakAuraReport;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import org.ocpsoft.prettytime.PrettyTime;

import java.util.Locale;

public class InterfaceReportListDto {

    private static final PrettyTime PT = new PrettyTime(Locale.ENGLISH);
    public static final String ANONYMOUS = "anonymous";

    private Long id;
    private String whatfor;
    private String postDate;
    private boolean handled;
    private String reporter;
    private String comments;

    public static InterfaceReportListDto create(InterfaceReport interfaceReport) {
        return new InterfaceReportListDto()
                .setId(interfaceReport.getId())
                .setHandled(interfaceReport.isHandled())
                .setPostDate(PT.format(interfaceReport.getPostDate()))
                .setReporter(extractReporter(interfaceReport.getReporter()))
                .setWhatfor(extractWhatFor(interfaceReport))
                .setComments(interfaceReport.getComment());
    }

    private static String extractWhatFor(InterfaceReport interfaceReport) {
        if (interfaceReport instanceof TellMeWhenReport) {
            return "tmw -> " + ((TellMeWhenReport) interfaceReport).getTellMeWhen().getId();
        } else if (interfaceReport instanceof WeakAuraReport) {
            return "wa -> " + ((WeakAuraReport) interfaceReport).getWeakAura().getId();
        } else if (interfaceReport instanceof MacroReport) {
            return "macro -> " + ((MacroReport) interfaceReport).getMacro().getId();
        } else {
           return "General Feedback";
        }
    }

    private static String extractReporter(ScrappieUser reporter) {
        if (reporter == null) {
            return ANONYMOUS;
        } else {
            return reporter.getUsername();
        }
    }

    public Long getId() {
        return id;
    }

    public InterfaceReportListDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getWhatfor() {
        return whatfor;
    }

    public InterfaceReportListDto setWhatfor(String whatfor) {
        this.whatfor = whatfor;
        return this;
    }

    public String getPostDate() {
        return postDate;
    }

    public InterfaceReportListDto setPostDate(String postDate) {
        this.postDate = postDate;
        return this;
    }

    public boolean isHandled() {
        return handled;
    }

    public InterfaceReportListDto setHandled(boolean handled) {
        this.handled = handled;
        return this;
    }

    public String getReporter() {
        return reporter;
    }

    public InterfaceReportListDto setReporter(String reporter) {
        this.reporter = reporter;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public InterfaceReportListDto setComments(String comments) {
        this.comments = comments;
        return this;
    }
}
