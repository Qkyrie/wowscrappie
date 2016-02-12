package com.deswaef.wowscrappie.ui.reports.service;

import com.deswaef.wowscrappie.ui.reports.domain.InterfaceReport;
import com.deswaef.wowscrappie.ui.reports.service.dto.CreateInterfaceReportDto;

import java.util.List;

public interface InterfaceReportService {
    void createReport(CreateInterfaceReportDto createInterfaceReportDto);

    List<InterfaceReport> findAll();

    void delete(Long id);

    void setHandled(Long id);

    InterfaceReport findOne(Long id);
}
