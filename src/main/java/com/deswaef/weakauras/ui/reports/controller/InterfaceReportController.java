package com.deswaef.weakauras.ui.reports.controller;

import com.deswaef.weakauras.ui.reports.controller.dto.CreateInterfaceReportDto;
import com.deswaef.weakauras.ui.reports.controller.dto.InterfaceReportListDto;
import com.deswaef.weakauras.ui.reports.service.InterfaceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/interface/reports")
public class InterfaceReportController {

    public static final String REPORTS_PAGE = "admin/reports/reports";
    @Autowired
    private InterfaceReportService interfaceReportService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public String findAll(ModelMap map) {
        map.put("reports", interfaceReportService.findAll()
                .stream()
                .map(InterfaceReportListDto::create)
                .collect(Collectors.toList()));
        return REPORTS_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/{id}")
    public @ResponseBody InterfaceReportListDto findOne(@PathVariable("id") Long id) {
        return InterfaceReportListDto.create(
                interfaceReportService.findOne(id)
        );
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public @ResponseBody boolean delete(@PathVariable("id") Long id) {
        try {
            interfaceReportService.delete(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}/handle", method = RequestMethod.GET)
    public @ResponseBody boolean handle(@PathVariable("id") Long id) {
        try {
            interfaceReportService.setHandled(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @RequestMapping(value = "/create", method = POST)
    public @ResponseBody boolean createReport(@RequestBody CreateInterfaceReportDto createInterfaceReportDto) {
        interfaceReportService.createReport(createInterfaceReportDto);
        return true;
    }

}
