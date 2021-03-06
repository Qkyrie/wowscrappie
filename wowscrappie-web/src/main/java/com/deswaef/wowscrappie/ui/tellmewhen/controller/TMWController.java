package com.deswaef.wowscrappie.ui.tellmewhen.controller;

import com.deswaef.wowscrappie.expansion.service.PatchCalculator;
import com.deswaef.wowscrappie.ui.image.domain.Screenshot;
import com.deswaef.wowscrappie.ui.tellmewhen.controller.dto.TellMeWhenDto;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.wowscrappie.ui.tellmewhen.service.TellMeWhenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/interface/tellmewhen")
@Controller
public class TMWController {

    @Autowired
    private TellMeWhenService tellMeWhenService;
    @Autowired
    private PatchCalculator patchCalculator;

    @RequestMapping("/{id}")
    public
    @ResponseBody
    TellMeWhenDto byId(@PathVariable("id") Long id) {
        Optional<TellMeWhen> byId = tellMeWhenService.findById(id);
        if (byId.isPresent()) {
            List<Screenshot> screenshots = tellMeWhenService.findScreenshots(byId.get());
            String[] resultArray = new String[screenshots.size()];
            return TellMeWhenDto.fromTellMeWhen(byId.get())
                    .setPatch(
                            patchCalculator.calculatePatch(
                                    byId.get().getLastUpdateDate())
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
    public
    @ResponseBody
    boolean approve(@PathVariable("id") Long id) {
        try {
            tellMeWhenService.approve(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/{id}/delete")
    public
    @ResponseBody
    boolean delete(@PathVariable("id") Long id) {
        try {
            tellMeWhenService.delete(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/{id}/disable")
    public
    @ResponseBody
    boolean disable(@PathVariable("id") Long id) {
        try {
            tellMeWhenService.disable(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
