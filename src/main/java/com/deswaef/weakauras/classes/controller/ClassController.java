package com.deswaef.weakauras.classes.controller;

import com.deswaef.weakauras.classes.domain.Spec;
import com.deswaef.weakauras.classes.domain.WowClass;
import com.deswaef.weakauras.classes.service.ClassService;
import com.deswaef.weakauras.classes.service.SpecService;
import com.deswaef.weakauras.ui.macros.controller.dto.MacroDto;
import com.deswaef.weakauras.ui.macros.service.MacroService;
import com.deswaef.weakauras.ui.rating.domain.ConfigRating;
import com.deswaef.weakauras.ui.rating.service.ConfigRatingService;
import com.deswaef.weakauras.ui.tellmewhen.controller.dto.TellMeWhenDto;
import com.deswaef.weakauras.ui.tellmewhen.service.TellMeWhenService;
import com.deswaef.weakauras.ui.weakauras.controller.dto.WeakAuraDto;
import com.deswaef.weakauras.ui.weakauras.service.WeakAuraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping(value = ClassController.CLASSES)
public class ClassController {

    public static final String WEAK_AURA = "weakaura";
    public static final String MACRO = "macro";
    public static final String TMW = "tellmewhen";
    public static final String CLASS_SPECIFIC = "class_specific";
    public static final long DEFAULT_RATING = 0;
    public static final String CLASSES = "/classes";

    @Autowired
    private ClassService classService;
    @Autowired
    private SpecService specService;
    @Autowired
    private TellMeWhenService tellMeWhenService;
    @Autowired
    private WeakAuraService weakAuraService;
    @Autowired
    private MacroService macroService;
    @Autowired
    private ConfigRatingService configRatingService;

    @RequestMapping("/{classslug}")
    public String byClassName(ModelMap modelmap, @PathVariable("classslug") String classslug) {
        Optional<WowClass> wowClass = classService.bySlug(classslug);
        if (wowClass.isPresent()) {
            WowClass wc = wowClass.get();
            modelmap.put("wowclass",
                    WowClassDto.fromWowClass(wc)
                            .setMacroAmount(macroService.countByWowClass(wc))
                            .setTmwAmount(tellMeWhenService.countByWowclass(wc))
                            .setWaAmount(weakAuraService.countByWowClass(wc))
            );
            Set<SpecDto> specs = specService.byClass(wc).stream()
                    .map(spec -> SpecDto.fromSpec(spec)
                            .setMacroAmount(macroService.countBySpec(spec))
                            .setTmwAmount(tellMeWhenService.countBySpec(spec))
                            .setWaAmount(weakAuraService.countBySpec(spec)))
                    .collect(Collectors.toSet());
            modelmap.put("specs", specs);
            modelmap.put("specnames", specs.stream().map(SpecDto::getName).collect(Collectors.joining(", ")));
            return "classes/specs";
        } else {
            return "classes/index";
        }
    }

    @RequestMapping("/{classslug}/{specslug}/{stringType}")
    public String getResults(ModelMap modelMap,
                             @PathVariable("classslug") String classslug,
                             @PathVariable("specslug") String specslug,
                             @PathVariable("stringType") String stringType) {
        Optional<WowClass> wowClass = classService.bySlug(classslug);
        if(wowClass.isPresent()) {
            boolean classSpecific = specslug.equals(CLASS_SPECIFIC);
            Optional<Spec> spec = specService.bySlug(wowClass.get(), specslug);
            if(spec.isPresent()) {
                modelMap.put("spec", spec.get());
            }
            if (spec.isPresent() || classSpecific) {
                //find the whatever stringtype per stuff
                modelMap.put("wowclass", wowClass.get());
                if (stringType.equals(TMW)) {
                    modelMap.put("uielements", getTellMeWhens(wowClass.get(), spec, classSpecific));
                    return "stringresults/fragments/results :: tmw";
                } else if (stringType.equals(MACRO)) {
                    modelMap.put("uielements", getMacros(wowClass.get(), spec,  classSpecific));
                    return "stringresults/fragments/results :: macro";
                } else if (stringType.equals(WEAK_AURA)) {
                    modelMap.put("uielements", getWeakauras(wowClass.get(), spec, classSpecific));
                    return "stringresults/fragments/results :: wa";
                } else {
                    return "stringresults/fragments/results :: error";
                }
            }
        }
        return "stringresults/fragments/results :: error";
    }

    private List<WeakAuraDto> getWeakauras(WowClass wowClass, Optional<Spec> spec, boolean classSpecific) {
        if(classSpecific) {
            return weakAuraService.findByWowClass(wowClass)
                    .stream()
                    .map(WeakAuraDto::fromWeakAura)
                    .map(x -> x.setRating(getRating(configRatingService.findByWeakAura(x.getId()))))
                    .sorted((wa1, wa2) -> new Long(wa2.getRating() - wa1.getRating()).intValue())
                    .collect(toList());
        } else {
            return weakAuraService.findBySpec(spec.get())
                    .stream()
                    .map(WeakAuraDto::fromWeakAura)
                    .map(x -> x.setRating(getRating(configRatingService.findByWeakAura(x.getId()))))
                    .sorted((wa1, wa2) -> new Long(wa2.getRating() - wa1.getRating()).intValue())
                    .collect(toList());
        }
    }

    private List<MacroDto> getMacros(WowClass wowClass, Optional<Spec> spec, boolean classSpecific) {
        if (classSpecific) {
            return macroService.findByWowClass(wowClass)
                    .stream()
                    .map(MacroDto::fromMacro)
                    .map(x -> x.setRating(getRating(configRatingService.findByMacro(x.getId()))))
                    .sorted((macro1, macro2) -> new Long(macro2.getRating() - macro1.getRating()).intValue())
                    .collect(toList());
        } else {
            return macroService.findBySpec(spec.get())
                    .stream()
                    .map(MacroDto::fromMacro)
                    .map(x -> x.setRating(getRating(configRatingService.findByMacro(x.getId()))))
                    .sorted((macro1, macro2) -> new Long(macro2.getRating() - macro1.getRating()).intValue())
                    .collect(toList());
        }
    }

    private List<TellMeWhenDto> getTellMeWhens(WowClass wowClass, Optional<Spec> spec, boolean classSpecific) {
        if(classSpecific) {
            return tellMeWhenService.findByWowclass(wowClass)
                    .stream()
                    .map(TellMeWhenDto::fromTellMeWhen)
                    .map(x -> x.setRating(getRating(configRatingService.findByTellMeWhen(x.getId()))))
                    .sorted((tmw1, tmw2) -> new Long(tmw2.getRating() - tmw1.getRating()).intValue())
                    .collect(toList());
        } else {
            return tellMeWhenService.findBySpec(spec.get())
                    .stream()
                    .map(TellMeWhenDto::fromTellMeWhen)
                    .map(x -> x.setRating(getRating(configRatingService.findByTellMeWhen(x.getId()))))
                    .sorted((tmw1, tmw2) -> new Long(tmw2.getRating() - tmw1.getRating()).intValue())
                    .collect(toList());
        }
    }

    private long getRating(Optional<? extends ConfigRating> configRating) {
        if (configRating.isPresent()) {
            return configRating.get().calculateEffectiveRating();
        } else {
            return DEFAULT_RATING;
        }
    }
}
