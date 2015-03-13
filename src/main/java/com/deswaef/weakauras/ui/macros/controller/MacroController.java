package com.deswaef.weakauras.ui.macros.controller;

import com.deswaef.weakauras.ui.macros.controller.dto.MacroDto;
import com.deswaef.weakauras.ui.macros.domain.Macro;
import com.deswaef.weakauras.ui.macros.service.MacroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping("/interface/macro")
public class MacroController {

    @Autowired
    private MacroService macroService;

    @RequestMapping("/{id}")
    public
    @ResponseBody
    MacroDto byId(@PathVariable("id") Long id) {
        Optional<Macro> macro = macroService.byId(id);
        if (macro.isPresent()) {
            return MacroDto.fromMacro(macro.get());
        } else {
            return null;
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/{id}/approve")
    public @ResponseBody boolean approve(@PathVariable("id") Long id) {
        try {
            macroService.approve(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/{id}/delete")
    public @ResponseBody boolean delete(@PathVariable("id") Long id) {
        try {
            macroService.delete(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/{id}/disable")
    public @ResponseBody boolean disable(@PathVariable("id") Long id) {
        try {
            macroService.disable(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
