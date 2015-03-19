package com.deswaef.weakauras.ui.tellmewhen.service;

import com.deswaef.weakauras.classes.domain.Spec;
import com.deswaef.weakauras.classes.domain.WowClass;
import com.deswaef.weakauras.ui.image.domain.Screenshot;
import com.deswaef.weakauras.ui.rating.service.ConfigRatingService;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhenScreenshot;
import com.deswaef.weakauras.ui.tellmewhen.repository.SpecTellMeWhenRepository;
import com.deswaef.weakauras.ui.tellmewhen.repository.TellMeWhenRepository;
import com.deswaef.weakauras.ui.tellmewhen.repository.TellMeWhenScreenshotRepository;
import com.deswaef.weakauras.ui.tellmewhen.repository.WowclassTellMeWhenRepository;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Long countByWowclass(WowClass wowClass) {
        if(isAdmin()) {
            return wowclassTellMeWhenRepository.countByWowClass(wowClass);
        } else {
            return wowclassTellMeWhenRepository.countByWowClassAndApproved(wowClass);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Long countBySpec(Spec spec) {
        if(isAdmin()) {
            return specTellMeWhenRepository.countBySpec(spec);
        } else {
            return specTellMeWhenRepository.countBySpecAndApproved(spec);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TellMeWhen> findById(Long id) {
        return tellMeWhenRepository.findOne(id);
    }

    @Override
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
            tellMeWhen.setApproved(true);
            tellMeWhenRepository.save(tellMeWhen);
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
