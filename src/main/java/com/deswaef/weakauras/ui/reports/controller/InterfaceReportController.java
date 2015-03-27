package com.deswaef.weakauras.ui.reports.controller;

import com.deswaef.weakauras.ui.reports.controller.dto.InterfaceReportDto;
import com.deswaef.weakauras.ui.reports.service.InterfaceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/interface/reports")
public class InterfaceReportController {

    @Autowired
    private InterfaceReportService interfaceReportService;

    @RequestMapping(value = "/create", method = POST)
    public @ResponseBody boolean createReport(@RequestBody InterfaceReportDto interfaceReportDto) {
        interfaceReportService.createReport(interfaceReportDto);
        return true;
    }

}
