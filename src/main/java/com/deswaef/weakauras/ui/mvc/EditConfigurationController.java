package com.deswaef.weakauras.ui.mvc;

import com.deswaef.weakauras.ui.macros.domain.Macro;
import com.deswaef.weakauras.ui.macros.service.MacroService;
import com.deswaef.weakauras.ui.mvc.dto.EditConfigurationDto;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.weakauras.ui.tellmewhen.service.TellMeWhenService;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
import com.deswaef.weakauras.ui.weakauras.service.WeakAuraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

import static com.deswaef.weakauras.ui.mvc.dto.EditConfigurationDto.create;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/admin/edit/{config}")
public class EditConfigurationController {

    @Autowired
    private MacroService macroService;
    @Autowired
    private TellMeWhenService tellMeWhenService;
    @Autowired
    private WeakAuraService weakAuraService;

    public static final String TMW = "tmw";
    public static final String WA = "wa";
    public static final String MACRO = "macro";

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = GET)
    public String index(ModelMap modelMap, @PathVariable("config") String config, @PathVariable("id") Long id) {
        Optional<EditConfigurationDto> returnValue = getConfig(config, id);
        if (returnValue.isPresent()) {
            modelMap.put("config", returnValue.get());
            return "admin/edit/index";
        } else {
            return "admin/edit/not_found";
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = POST)
    public @ResponseBody EditConfigurationDto edit(@RequestBody EditConfigurationDto dto, @PathVariable("config") String config, @PathVariable("id") Long id) {
        if(!id.equals(dto.getId())) {
            return dto
                    .setHasErrors(true)
                    .setErrorMessage("Something unexpected went wrong, hacking stuff? o.o");
        }
        try {
            saveEdit(config, dto);
            return dto;
        } catch (Exception ex) {
            return dto
                    .setHasErrors(true)
                    .setErrorMessage(ex.getMessage());
        }
    }

    private void saveEdit(String config, EditConfigurationDto dto) {
        if (config.equals(TMW)) {
            tellMeWhenService.edit(dto);
        } else if (config.equals(WA)) {
            weakAuraService.edit(dto);
        } else if (config.equals(MACRO)) {
            macroService.edit(dto);
        } else {
            throw new IllegalArgumentException("Configuration could not be found");
        }
    }

    private Optional<EditConfigurationDto> getConfig(String config, Long id) {
        if (config.equals(TMW)) {
            Optional<TellMeWhen> byId = tellMeWhenService.findById(id);
            if (byId.isPresent()) {
                return convert(byId.get());
            } else {
                return Optional.empty();
            }
        } else if (config.equals(WA)) {
            Optional<WeakAura> weakAura = weakAuraService.byId(id);
            if (weakAura.isPresent()) {
                return convert(weakAura.get());
            } else {
                return Optional.empty();
            }
        } else if (config.equals(MACRO)) {
            Optional<Macro> macro = macroService.byId(id);
            if (macro.isPresent()) {
                return convert(macro.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    private Optional<EditConfigurationDto> convert(Macro macro) {
        return Optional.of(
                create()
                        .setActualValue(macro.getActualValue())
                        .setCaption(macro.getName())
                        .setComments(macro.getComment())
                        .setType(MACRO)
                        .setUploader(macro.getUploader().getUsername())
                        .setId(macro.getId())
        );
    }

    private Optional<EditConfigurationDto> convert(WeakAura weakAura) {
        return Optional.of(
                create()
                        .setId(weakAura.getId())
                        .setUploader(weakAura.getUploader().getUsername())
                        .setType(WA)
                        .setComments(weakAura.getComment())
                        .setCaption(weakAura.getName())
                        .setActualValue(weakAura.getActualValue())
        );
    }

    private Optional<EditConfigurationDto> convert(TellMeWhen tellMeWhen) {
        return Optional.of(
                create()
                        .setId(tellMeWhen.getId())
                        .setActualValue(tellMeWhen.getActualValue())
                        .setCaption(tellMeWhen.getName())
                        .setComments(tellMeWhen.getComment())
                        .setUploader(tellMeWhen.getUploader().getUsername())
                        .setType(TMW)
        );
    }

}
