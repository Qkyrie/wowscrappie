package com.deswaef.weakauras.classes.service;

import com.deswaef.weakauras.classes.domain.Spec;
import com.deswaef.weakauras.classes.domain.WowClass;
import com.deswaef.weakauras.classes.repository.SpecRepository;
import com.deswaef.weakauras.classes.repository.WowClassRepository;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class SpecServiceImpl implements SpecService {

    @Autowired
    private WowClassRepository wowClassRepository;
    @Autowired
    private SpecRepository specRepository;

    @Override
    @Transactional(readOnly = true)
    public Set<Spec> byClass(WowClass wowClass) {
        Optional<WowClass> one = wowClassRepository.findOne(wowClass.getId());
        if(one.isPresent()) {
            return one.get().getSpecs();
        } else {
            return Sets.newHashSet();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Spec> bySlug(WowClass wowClass, String slug) {
        return specRepository.findBySlugAndWowClass(slug, wowClass);
    }
}
