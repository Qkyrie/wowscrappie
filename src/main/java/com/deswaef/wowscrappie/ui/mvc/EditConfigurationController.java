package com.deswaef.wowscrappie.ui.mvc;

import com.deswaef.wowscrappie.ui.macros.domain.Macro;
import com.deswaef.wowscrappie.ui.macros.domain.MacroConfigRating;
import com.deswaef.wowscrappie.ui.macros.service.MacroService;
import com.deswaef.wowscrappie.ui.dto.EditConfigurationDto;
import com.deswaef.wowscrappie.ui.rating.service.ConfigRatingService;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.TellMeWhenConfigRating;
import com.deswaef.wowscrappie.ui.tellmewhen.service.TellMeWhenService;
import com.deswaef.wowscrappie.ui.weakauras.domain.WeakAura;
import com.deswaef.wowscrappie.ui.weakauras.domain.WeakAuraConfigRating;
import com.deswaef.wowscrappie.ui.weakauras.service.WeakAuraService;
import com.deswaef.wowscrappie.usermanagement.domain.RoleEnum;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

import static com.deswaef.wowscrappie.ui.dto.EditConfigurationDto.create;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/personal/edit/{config}")
public class EditConfigurationController {

    @Autowired
    private MacroService macroService;
    @Autowired
    private TellMeWhenService tellMeWhenService;
    @Autowired
    private WeakAuraService weakAuraService;
    @Autowired
    private ConfigRatingService configRatingService;

    public static final String TMW = "tmw";
    public static final String WA = "wa";
    public static final String MACRO = "macro";

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/{id}", method = GET)
    public String index(ModelMap modelMap, @PathVariable("config") String config, @PathVariable("id") Long id) {
        Optional<EditConfigurationDto> returnValue = getConfig(config, id);
        if (returnValue.isPresent()) {
            modelMap.put("config", returnValue.get());
            return "personal/edit/index";
        } else {
            return "personal/edit/not_found";
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/{id}", method = POST)
    public
    @ResponseBody
    EditConfigurationDto edit(@RequestBody EditConfigurationDto dto, @PathVariable("config") String config, @PathVariable("id") Long id) {
        if (!id.equals(dto.getId())) {
            return dto
                    .setHasErrors(true)
                    .setErrorMessage("Something unexpected went wrong, hacking stuff? o.o");
        }
        try {
            if (getConfig(config, id).isPresent()) {
                saveEdit(config, dto);
                return dto;
            } else {
                throw new IllegalArgumentException("You are not the original creator of the configuration");
            }
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
            if (byId.isPresent() && isCreatorOrAdmin(byId.get())) {
                return convert(byId.get());
            } else {
                return Optional.empty();
            }
        } else if (config.equals(WA)) {
            Optional<WeakAura> weakAura = weakAuraService.byId(id);
            if (weakAura.isPresent() && isCreatorOrAdmin(weakAura.get())) {
                return convert(weakAura.get());
            } else {
                return Optional.empty();
            }
        } else if (config.equals(MACRO)) {
            Optional<Macro> macro = macroService.byId(id);
            if (macro.isPresent() && isCreatorOrAdmin(macro.get())) {
                return convert(macro.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    private boolean isCreatorOrAdmin(TellMeWhen tellMeWhen) {
        Object pr = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((pr instanceof ScrappieUser && pr.equals(tellMeWhen.getUploader())) || ((ScrappieUser) pr).getAuthorities().stream().anyMatch(x -> x.getAuthority().equals(RoleEnum.ADMIN_ROLE.getUserRole())));
    }

    private boolean isCreatorOrAdmin(Macro macro) {
        Object pr = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((pr instanceof ScrappieUser && pr.equals(macro.getUploader())) || ((ScrappieUser) pr).getAuthorities().stream().anyMatch(x -> x.getAuthority().equals(RoleEnum.ADMIN_ROLE.getUserRole())));
    }

    private boolean isCreatorOrAdmin(WeakAura weakAura) {
        Object pr = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((pr instanceof ScrappieUser && pr.equals(weakAura.getUploader())) || ((ScrappieUser) pr).getAuthorities().stream().anyMatch(x -> x.getAuthority().equals(RoleEnum.ADMIN_ROLE.getUserRole())));
    }

    private Optional<EditConfigurationDto> convert(Macro macro) {
        EditConfigurationDto configDto = create()
                .setActualValue(macro.getActualValue())
                .setCaption(macro.getName())
                .setComments(macro.getComment())
                .setType(MACRO)
                .setUploader(macro.getUploader().getUsername())
                .setId(macro.getId());
        Optional<MacroConfigRating> byMacro = configRatingService.findByMacro(macro.getId());
        if (byMacro.isPresent()) {
            configDto.setRating(byMacro.get().calculateEffectiveRating());
        }
        return Optional.of(
                configDto
        );
    }

    private Optional<EditConfigurationDto> convert(WeakAura weakAura) {
        EditConfigurationDto configDto = create()
                .setId(weakAura.getId())
                .setUploader(weakAura.getUploader().getUsername())
                .setType(WA)
                .setComments(weakAura.getComment())
                .setCaption(weakAura.getName())
                .setActualValue(weakAura.getActualValue());
        Optional<WeakAuraConfigRating> byWeakAura = configRatingService.findByWeakAura(weakAura.getId());
        if (byWeakAura.isPresent()) {
            configDto.setRating(byWeakAura.get().calculateEffectiveRating());
        }
        return Optional.of(
                configDto
        );
    }

    private Optional<EditConfigurationDto> convert(TellMeWhen tellMeWhen) {
        EditConfigurationDto configDto = create()
                .setId(tellMeWhen.getId())
                .setActualValue(tellMeWhen.getActualValue())
                .setCaption(tellMeWhen.getName())
                .setComments(tellMeWhen.getComment())
                .setUploader(tellMeWhen.getUploader().getUsername())
                .setType(TMW);
        Optional<TellMeWhenConfigRating> byTellMeWhen = configRatingService.findByTellMeWhen(tellMeWhen.getId());
        if (byTellMeWhen.isPresent()) {
            configDto.setRating(byTellMeWhen.get().calculateEffectiveRating());
        }
        return Optional.of(
                configDto
        );
    }

}
