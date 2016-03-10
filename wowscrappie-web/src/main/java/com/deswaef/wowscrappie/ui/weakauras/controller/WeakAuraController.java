package com.deswaef.wowscrappie.ui.weakauras.controller;

import com.deswaef.wowscrappie.expansion.service.PatchCalculator;
import com.deswaef.wowscrappie.ui.image.domain.Screenshot;
import com.deswaef.wowscrappie.ui.weakauras.controller.dto.WeakAuraDto;
import com.deswaef.wowscrappie.ui.weakauras.domain.WeakAura;
import com.deswaef.wowscrappie.ui.weakauras.service.WeakAuraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/interface/weakaura")
public class WeakAuraController {

    @Autowired
    private WeakAuraService weakAuraService;
    @Autowired
    private PatchCalculator patchCalculator;

    @RequestMapping("/{id}")
    public
    @ResponseBody
    WeakAuraDto byId(@PathVariable("id") Long id) {
        Optional<WeakAura> weakAura = weakAuraService.byId(id);
        if (weakAura.isPresent()) {
            List<Screenshot> screenshots = weakAuraService.findScreenshots(weakAura.get());
            String[] resultArray = new String[screenshots.size()];
            return WeakAuraDto.fromWeakAura(weakAura.get())
                    .setPatch(
                            patchCalculator.calculatePatch(
                                    weakAura.get().getLastUpdateDate())
                                    .orElse(null)
                    )
                    .setImageRefs(screenshots
                            .stream()
                            .map(Screenshot::getReference)
                            .collect(Collectors.toList()).toArray(resultArray));
        } else {
            return null;
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/{id}/approve")
    public @ResponseBody boolean approve(@PathVariable("id") Long id) {
        try {
            weakAuraService.approve(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/{id}/delete")
    public @ResponseBody boolean delete(@PathVariable("id") Long id) {
        try {
            weakAuraService.delete(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/{id}/disable")
    public @ResponseBody boolean disable(@PathVariable("id") Long id) {
        try {
            weakAuraService.disable(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
