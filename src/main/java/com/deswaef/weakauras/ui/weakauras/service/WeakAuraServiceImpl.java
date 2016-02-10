package com.deswaef.weakauras.ui.weakauras.service;

import com.deswaef.weakauras.classes.domain.Spec;
import com.deswaef.weakauras.classes.domain.WowClass;
import com.deswaef.weakauras.notifications.controller.dto.PersistentNotificationDto;
import com.deswaef.weakauras.notifications.service.PersistentNotificationService;
import com.deswaef.weakauras.raids.domain.Boss;
import com.deswaef.weakauras.ui.image.domain.Screenshot;
import com.deswaef.weakauras.ui.mvc.dto.EditConfigurationDto;
import com.deswaef.weakauras.ui.rating.service.ConfigRatingService;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
import com.deswaef.weakauras.ui.weakauras.domain.WeakauraScreenshot;
import com.deswaef.weakauras.ui.weakauras.repository.*;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
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
public class WeakAuraServiceImpl implements WeakAuraService {

    @Autowired
    private WeakAuraRepository weakAuraRepository;
    @Autowired
    private WowclassWeakAuraRepository wowclassWeakAuraRepository;
    @Autowired
    private SpecWeakAuraRepository specWeakAuraRepository;
    @Autowired
    private WeakAuraScreenshotRepository weakAuraScreenshotRepository;
    @Autowired
    private ConfigRatingService configRatingService;
    @Autowired
    private BossFightWeakAuraRepository bossFightWeakAuraRepository;
    @Autowired
    private PersistentNotificationService persistentNotificationService;

    @Override
    @Transactional
    public WeakAura createWeakAura(WeakAura weakAura) {
        return weakAuraRepository.save(weakAura);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WeakAura> findByWowClass(WowClass wowClass) {
        if (isAdmin()) {
            return wowclassWeakAuraRepository.findByWowClass(wowClass);
        } else {
            return wowclassWeakAuraRepository.findByWowClassAndApproved(wowClass);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<WeakAura> findBySpec(Spec spec) {
        if (isAdmin()) {
            return specWeakAuraRepository.findBySpec(spec);
        } else {
            return specWeakAuraRepository.findBySpecAndApproved(spec);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<WeakAura> findByBoss(Boss boss) {
        if (isAdmin()) {
            return bossFightWeakAuraRepository.findByBoss(boss);
        } else {
            return bossFightWeakAuraRepository.findByBossAndApproved(boss);
        }
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "amountscache", key = "'waCount-wowclass-'.concat(#wowClass.id)")
    public Long countByWowClass(WowClass wowClass) {
        return wowclassWeakAuraRepository.countByWowClassAndApproved(wowClass);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "amountscache", key = "'waCount-spec-'.concat(#spec.id)")
    public Long countBySpec(Spec spec) {
        return specWeakAuraRepository.countBySpecAndApproved(spec);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "amountscache", key = "'waCount-boss-'.concat(#boss.id)")
    public Long countByBoss(Boss boss) {
        return bossFightWeakAuraRepository.countByBossAndApproved(boss);
    }

    @Override
    public Optional<WeakAura> byId(Long id) {
        return weakAuraRepository.findOne(id);
    }

    @Override
    @Cacheable(value = "amountscache", key = "'waCount'")
    public long count() {
        return weakAuraRepository.countApproved(true);
    }

    @Override
    @Transactional
    public void saveScreenshot(String reference, WeakAura weakAura) {
        WeakauraScreenshot weakauraScreenshot = (WeakauraScreenshot) new WeakauraScreenshot().setWeakAura(weakAura).setReference(reference);
        weakAuraScreenshotRepository.save(weakauraScreenshot);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Screenshot> findScreenshots(WeakAura weakAura) {
        Optional<WeakAura> one = weakAuraRepository.findOne(weakAura.getId());
        if (one.isPresent()) {
            return weakAuraScreenshotRepository.findByWeakAura(one.get());
        } else {
            return Lists.newArrayList();
        }
    }

    @Override
    public List<WeakAura> findAll() {
        if (isAdmin()) {
            return weakAuraRepository.findAll();
        } else {
            return weakAuraRepository.findAllApproved(true);
        }

    }

    @Override
    @Transactional
    public void approve(Long id) {
        Optional<WeakAura> one = weakAuraRepository.findOne(id);
        if (one.isPresent()) {
            WeakAura weakAura = one.get();
            if (!weakAura.isApproved()) {
                weakAura.setApproved(true);
                WeakAura savedWeakAura = weakAuraRepository.save(weakAura);
                persistentNotificationService.createPersistentNotification(
                        weakAura.getUploader(),
                        PersistentNotificationDto.create()
                                .setContent(String.format("An admin just approved your weakaura (%s)", savedWeakAura.getName()))
                                .setUrl(String.format("/shared/wa/%d", savedWeakAura.getId()))
                                .setTitle("Weakaura approved!")
                );
            }
        } else {
            throw new IllegalArgumentException("A weakaura with that id was not found");
        }
    }

    @Override
    @Transactional
    public void disable(Long id) {
        Optional<WeakAura> one = weakAuraRepository.findOne(id);
        if (one.isPresent()) {
            WeakAura weakAura = one.get();
            weakAura.setApproved(false);
            weakAuraRepository.save(weakAura);
        } else {
            throw new IllegalArgumentException("A weakaura with that id was not found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<WeakAura> findAllFromUser(ScrappieUser scrappieUser) {
        return weakAuraRepository.findByUploader(scrappieUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WeakAura> findAllNonApproved() {
        return weakAuraRepository.findAllApproved(false);
    }

    @Override
    @Transactional(readOnly = true)
    public long countAllFromUser(ScrappieUser scrappieUser) {
        return weakAuraRepository.countByUploader(scrappieUser);
    }

    @Override
    @Transactional(readOnly = true)
    public long countUnapproved() {
        return weakAuraRepository.countApproved(false);
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
                Optional<WeakAura> one = weakAuraRepository.findOne(dto.getId());
                if (one.isPresent()) {
                    weakAuraRepository.save(
                            one.get()
                                    .setLastUpdateDate(now())
                                    .setActualValue(dto.getActualValue())
                                    .setName(dto.getCaption())
                                    .setComment(dto.getComments())
                    );
                } else {
                    throw new IllegalArgumentException("A weakaura with that id could not be found");
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
    public void delete(Long id) {
        configRatingService.deleteWeakAuraConfigRating(id);
        deleteScreenshotsFor(id);
        weakAuraRepository.delete(id);
    }

    private void deleteScreenshotsFor(Long id) {
        Optional<WeakAura> one = weakAuraRepository.findOne(id);
        if (one.isPresent()) {
            List<Screenshot> byWeakAura = weakAuraScreenshotRepository.findByWeakAura(one.get());
            if (!byWeakAura.isEmpty()) {
                byWeakAura.forEach(ss -> weakAuraScreenshotRepository.delete(ss.getId()));
            }
        }
    }

}
