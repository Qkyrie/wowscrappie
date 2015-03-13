package com.deswaef.weakauras.ui.macros.service;

import com.deswaef.weakauras.classes.domain.Spec;
import com.deswaef.weakauras.classes.domain.WowClass;
import com.deswaef.weakauras.ui.macros.domain.Macro;
import com.deswaef.weakauras.ui.macros.repository.MacroRepository;
import com.deswaef.weakauras.ui.macros.repository.SpecMacroRepository;
import com.deswaef.weakauras.ui.macros.repository.WowClassMacroRepository;
import com.deswaef.weakauras.ui.rating.service.ConfigRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MacroServiceImpl implements MacroService {

    @Autowired
    private MacroRepository macroRepository;
    @Autowired
    private SpecMacroRepository specMacroRepository;
    @Autowired
    private WowClassMacroRepository wowClassMacroRepository;
    @Autowired
    private ConfigRatingService configRatingService;

    @Override
    @Transactional
    public Macro newMacro(Macro macro) {
        return macroRepository.save(macro);
    }

    @Override
    public List<Macro> findByWowClass(WowClass wowClass) {
        if(isAdmin()) {
            return wowClassMacroRepository.findByWowClass(wowClass);
        } else {
            return wowClassMacroRepository.findByWowClassAndApproved(wowClass);
        }
    }

    @Override
    public List<Macro> findBySpec(Spec spec) {
        if(isAdmin()){
            return specMacroRepository.findBySpec(spec);
        } else {
            return specMacroRepository.findBySpecAndApproved(spec);
        }
    }

    @Override
    public Long countByWowClass(WowClass wowClass) {
        if(isAdmin()) {
            return wowClassMacroRepository.countByWowClass(wowClass);
        } else {
            return wowClassMacroRepository.countByWowClassAndApproved(wowClass);
        }
    }

    @Override
    public Long countBySpec(Spec spec) {
        if(isAdmin()){
            return specMacroRepository.countBySpec(spec);
        } else {
            return specMacroRepository.countBySpecAndApproved(spec);
        }
    }

    @Override
    public Optional<Macro> byId(Long id) {
        return macroRepository.findOne(id);
    }

    @Override
    public long count() {
        return macroRepository.countApproved();
    }

    @Override
    public List<Macro> findAll() {
        if(isAdmin()){
            return macroRepository.findAll();
        } else {
            return macroRepository.findAllApproved();
        }
    }

    @Override
    public void approve(Long id) {
        Optional<Macro> one = macroRepository.findOne(id);
        if (one.isPresent()) {
            Macro macro = one.get();
            macro.setApproved(true);
            macroRepository.save(macro);
        } else {
            throw new IllegalArgumentException("a macro with that id was not found");
        }
    }


    @Override
    @Transactional
    public void disable(Long id) {
        Optional<Macro> one = macroRepository.findOne(id);
        if (one.isPresent()) {
            Macro macro = one.get();
            macro.setApproved(false);
            macroRepository.save(macro);
        } else {
            throw new IllegalArgumentException("a macro with that id was not found");
        }
    }

    @Override
    @Transactional
    public void delete(Long macro) {
        configRatingService.deleteMacroConfigRating(macro);
        macroRepository.delete(macro);
    }

}
