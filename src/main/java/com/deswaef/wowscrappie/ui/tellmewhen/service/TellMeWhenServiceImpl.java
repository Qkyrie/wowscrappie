package com.deswaef.wowscrappie.ui.tellmewhen.service;

import com.deswaef.wowscrappie.classes.domain.Spec;
import com.deswaef.wowscrappie.classes.domain.WowClass;
import com.deswaef.wowscrappie.notifications.controller.dto.PersistentNotificationDto;
import com.deswaef.wowscrappie.notifications.service.PersistentNotificationService;
import com.deswaef.wowscrappie.raids.domain.Boss;
import com.deswaef.wowscrappie.ui.image.domain.Screenshot;
import com.deswaef.wowscrappie.ui.mvc.dto.EditConfigurationDto;
import com.deswaef.wowscrappie.ui.rating.service.ConfigRatingService;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.TellMeWhenScreenshot;
import com.deswaef.wowscrappie.ui.tellmewhen.repository.BossFightTellMeWhenRepository;
import com.deswaef.wowscrappie.ui.tellmewhen.repository.SpecTellMeWhenRepository;
import com.deswaef.wowscrappie.ui.tellmewhen.repository.TellMeWhenRepository;
import com.deswaef.wowscrappie.ui.tellmewhen.repository.TellMeWhenScreenshotRepository;
import com.deswaef.wowscrappie.ui.tellmewhen.repository.WowclassTellMeWhenRepository;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TellMeWhenServiceImpl implements TellMeWhenService {

    @Autowired
    private TellMeWhenRepository tellMeWhenRepository;
    @Autowired
    private WowclassTellMeWhenRepository wowclassTellMeWhenRepository;
    @Autowired
    private SpecTellMeWhenRepository specTellMeWhenRepository;
    @Autowired
    private TellMeWhenScreenshotRepository tellMeWhenScreenshotRepository;
    @Autowired
    private ConfigRatingService configRatingService;
    @Autowired
    private BossFightTellMeWhenRepository bossFightTellMeWhenRepository;
    @Autowired
    private PersistentNotificationService persistentNotificationService;

    @Override
    @Transactional
    public TellMeWhen createTMW(TellMeWhen tellMeWhen) {
        return tellMeWhenRepository.save(tellMeWhen);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TellMeWhen> findByWowclass(WowClass wowClass) {
        if(isAdmin()) {
            return wowclassTellMeWhenRepository.findByWowClass(wowClass);
        } else {
            return wowclassTellMeWhenRepository.findByWowClassAndApproved(wowClass);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<TellMeWhen> findBySpec(Spec spec) {
        if(isAdmin()) {
            return specTellMeWhenRepository.findBySpec(spec);
        } else {
            return specTellMeWhenRepository.findBySpecAndApproved(spec);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<TellMeWhen> findByBoss(Boss boss) {
        if(isAdmin()) {
            return bossFightTellMeWhenRepository.findByBoss(boss);
        } else {
            return bossFightTellMeWhenRepository.findByBossAndApproved(boss);
        }
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "amountscache", key = "'tmwCount-wowclass-'.concat(#wowClass.id)")
    public Long countByWowclass(WowClass wowClass) {
        if(isAdmin()) {
            return wowclassTellMeWhenRepository.countByWowClass(wowClass);
        } else {
            return wowclassTellMeWhenRepository.countByWowClassAndApproved(wowClass);
        }
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "amountscache", key = "'tmwCount-spec-'.concat(#spec.id)")
    public Long countBySpec(Spec spec) {
        if(isAdmin()) {
            return specTellMeWhenRepository.countBySpec(spec);
        } else {
            return specTellMeWhenRepository.countBySpecAndApproved(spec);
        }
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "amountscache", key = "'tmwCount-boss-'.concat(#boss.id)")
    public Long countByBoss(Boss boss) {
        if(isAdmin()) {
            return bossFightTellMeWhenRepository.countByBoss(boss);
        } else {
            return bossFightTellMeWhenRepository.countByBossAndApproved(boss);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TellMeWhen> findById(Long id) {
        return tellMeWhenRepository.findOne(id);
    }

    @Override
    @Cacheable(value = "amountscache", key = "'tmwCount'")
    public long count() {
        return tellMeWhenRepository.countApproved(true);
    }

    @Override
    public void saveScreenshot(String reference, TellMeWhen tellMeWhen) {
        TellMeWhenScreenshot tellMeWhenScreenshot = (TellMeWhenScreenshot) new TellMeWhenScreenshot().setTellMeWhen(tellMeWhen).setReference(reference);
        tellMeWhenScreenshotRepository.save(tellMeWhenScreenshot);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Screenshot> findScreenshots(TellMeWhen tellMeWhen) {
        Optional<TellMeWhen> one = tellMeWhenRepository.findOne(tellMeWhen.getId());
        if(one.isPresent()) {
            return tellMeWhenScreenshotRepository.findByTellMeWhen(one.get());
        } else {
            return Lists.newArrayList();
        }
    }

    @Override
    public List<TellMeWhen> findAll() {

        return tellMeWhenRepository.findAll();
    }

    @Override
    @Transactional
    public void approve(Long id) {
        Optional<TellMeWhen> one = tellMeWhenRepository.findOne(id);
        if (one.isPresent()) {
            TellMeWhen tellMeWhen = one.get();
            if(!tellMeWhen.isApproved()) {
                tellMeWhen.setApproved(true);
                TellMeWhen savedTellMeWhen = tellMeWhenRepository.save(tellMeWhen);
                persistentNotificationService.createPersistentNotification(
                        savedTellMeWhen.getUploader(),
                        PersistentNotificationDto.create()
                                .setContent(String.format("An admin just approved your tellmewhen (%s)", savedTellMeWhen.getName()))
                                .setUrl(String.format("/shared/tmw/%d", savedTellMeWhen.getId()))
                                .setTitle("TellMeWhen approved!")
                );
            }
        } else {
            throw new IllegalArgumentException("a tmw-config with that id was not found");
        }
    }

    @Override
    @Transactional
    public void disable(Long tmw) {
        Optional<TellMeWhen> one = tellMeWhenRepository.findOne(tmw);
        if (one.isPresent()) {
            TellMeWhen tellMeWhen = one.get();
            tellMeWhen.setApproved(false);
            tellMeWhenRepository.save(tellMeWhen);
        } else {
            throw new IllegalArgumentException("a tmw-config with that id was not found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<TellMeWhen> findAllFromUser(ScrappieUser scrappieUser) {
        return tellMeWhenRepository.findByUploader(scrappieUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TellMeWhen> findAllNonApproved() {
        return tellMeWhenRepository.findAllApproved(false);
    }

    @Override
    @Transactional(readOnly = true)
    public long countAllFromUser(ScrappieUser scrappieUser) {
        return tellMeWhenRepository.countByUploader(scrappieUser);
    }

    @Override
    @Transactional(readOnly = true)
    public long countUnapproved() {
        return tellMeWhenRepository.countApproved(false);
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
                Optional<TellMeWhen> one = tellMeWhenRepository.findOne(dto.getId());
                if (one.isPresent()) {
                    tellMeWhenRepository.save(
                            one.get()
                                    .setLastUpdateDate(now())
                                    .setActualValue(dto.getActualValue())
                                    .setName(dto.getCaption())
                                    .setComment(dto.getComments())
                    );
                } else {
                    throw new IllegalArgumentException("A tellmewhen with that id could not be found");
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
    public void delete(Long tmw) {
        configRatingService.deleteTellMeWhenConfigRating(tmw);
        deleteScreenshotsFor(tmw);
        tellMeWhenRepository.delete(tmw);
    }



    private void deleteScreenshotsFor(Long id) {
        Optional<TellMeWhen> one = tellMeWhenRepository.findOne(id);
        if(one.isPresent()) {
            List<Screenshot> byTellMeWhen = tellMeWhenScreenshotRepository.findByTellMeWhen(one.get());
            if (!byTellMeWhen.isEmpty()) {
                byTellMeWhen.forEach(ss -> tellMeWhenScreenshotRepository.delete(ss.getId()));
            }
        }
    }
}
