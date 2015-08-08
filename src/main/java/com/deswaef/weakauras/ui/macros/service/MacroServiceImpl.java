package com.deswaef.weakauras.ui.macros.service;

import com.deswaef.weakauras.classes.domain.Spec;
import com.deswaef.weakauras.classes.domain.WowClass;
import com.deswaef.weakauras.notifications.controller.dto.PersistentNotificationDto;
import com.deswaef.weakauras.notifications.service.PersistentNotificationService;
import com.deswaef.weakauras.raids.domain.Boss;
import com.deswaef.weakauras.ui.macros.domain.Macro;
import com.deswaef.weakauras.ui.macros.repository.BossMacroRepository;
import com.deswaef.weakauras.ui.macros.repository.MacroRepository;
import com.deswaef.weakauras.ui.macros.repository.SpecMacroRepository;
import com.deswaef.weakauras.ui.macros.repository.WowClassMacroRepository;
import com.deswaef.weakauras.ui.mvc.dto.EditConfigurationDto;
import com.deswaef.weakauras.ui.rating.service.ConfigRatingService;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    @Autowired
    private BossMacroRepository bossMacroRepository;
    @Autowired
    private PersistentNotificationService persistentNotificationService;

    @Override
    @Transactional
    public Macro newMacro(Macro macro) {
        return macroRepository.save(macro);
    }

    @Override
    public List<Macro> findByWowClass(WowClass wowClass) {
        if (isAdmin()) {
            return wowClassMacroRepository.findByWowClass(wowClass);
        } else {
            return wowClassMacroRepository.findByWowClassAndApproved(wowClass);
        }
    }

    @Override
    public List<Macro> findBySpec(Spec spec) {
        if (isAdmin()) {
            return specMacroRepository.findBySpec(spec);
        } else {
            return specMacroRepository.findBySpecAndApproved(spec);
        }
    }

    @Override
    public List<Macro> findByBoss(Boss boss) {
        if (isAdmin()) {
            return bossMacroRepository.findByBoss(boss);
        } else {
            return bossMacroRepository.findByBossAndApproved(boss);
        }
    }

    @Override
    @Cacheable(value = "amountscache", key = "'macroCount-wowclass-'.concat(#wowClass.id)")
    public Long countByWowClass(WowClass wowClass) {
        return wowClassMacroRepository.countByWowClassAndApproved(wowClass);
    }

    @Override
    @Cacheable(value = "amountscache", key = "'macroCount-spec-'.concat(#spec.id)")
    public Long countBySpec(Spec spec) {
        return specMacroRepository.countBySpecAndApproved(spec);
    }

    @Override
    @Cacheable(value = "amountscache", key = "'macroCount-boss-'.concat(#boss.id)")
    public Long countByBoss(Boss boss) {
        return bossMacroRepository.countByBossAndApproved(boss);
    }

    @Override
    public Optional<Macro> byId(Long id) {
        return macroRepository.findOne(id);
    }

    @Override
    @Cacheable(value = "amountscache", key = "'macroCount'")
    public long count() {
        return macroRepository.countApproved(true);
    }

    @Override
    public long countUnapproved() {
        return macroRepository.countApproved(false);
    }

    @Override
    public List<Macro> findAll() {
        if (isAdmin()) {
            return macroRepository.findAll();
        } else {
            return macroRepository.findAllApproved(true);
        }
    }

    @Override
    public void approve(Long id) {
        Optional<Macro> one = macroRepository.findOne(id);
        if (one.isPresent()) {
            Macro macro = one.get();
            if (!macro.isApproved()) {
                macro.setApproved(true);
                macroRepository.save(macro);
                persistentNotificationService.createPersistentNotification(
                        macro.getUploader(),
                        PersistentNotificationDto.create()
                                .setContent(String.format("An admin just approved your macro (%s)", macro.getName()))
                                .setUrl(String.format("/shared/macro/%d", macro.getId()))
                                .setTitle("Macro approved!")
                );
            }
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
    @Transactional(readOnly = true)
    public List<Macro> findAllFromUser(ScrappieUser scrappieUser) {
        return macroRepository.findByUploader(scrappieUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Macro> findAllUnapproved() {
        return macroRepository.findAllApproved(false);
    }

    @Override
    @Transactional(readOnly = true)
    public long countAllFromUser(ScrappieUser scrappieUser) {
        return macroRepository.countByUploader(scrappieUser);
    }

    @Override
    @Transactional
    public void edit(EditConfigurationDto dto) {
        if (Strings.isNullOrEmpty(dto.getActualValue())) {
            throw new IllegalArgumentException("actual value cannot be empty");
        } else if (Strings.isNullOrEmpty(dto.getCaption())) {
            throw new IllegalArgumentException("Caption cannot be empty");
        } else {
            try {
                Optional<Macro> one = macroRepository.findOne(dto.getId());
                if (one.isPresent()) {
                    macroRepository.save(
                            one.get()
                                    .setLastUpdateDate(now())
                                    .setActualValue(dto.getActualValue())
                                    .setName(dto.getCaption())
                                    .setDescription(dto.getComments())
                    );
                } else {
                    throw new IllegalArgumentException("A macro with that id could not be found");
                }
            } catch (Exception ex) {
                throw new IllegalArgumentException("Couldn't edit the macro");
            }
        }
    }

    private Date now() {
        return new Date();
    }

    @Override
    @Transactional
    public void delete(Long macro) {
        configRatingService.deleteMacroConfigRating(macro);
        macroRepository.delete(macro);
    }

}
