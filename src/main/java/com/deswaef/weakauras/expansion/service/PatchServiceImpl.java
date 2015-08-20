package com.deswaef.weakauras.expansion.service;

import com.deswaef.weakauras.configuration.CacheAbstractionConfiguration;
import com.deswaef.weakauras.expansion.domain.Patch;
import com.deswaef.weakauras.expansion.repository.PatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class PatchServiceImpl implements PatchService {

    @Autowired
    private PatchRepository patchRepository;

    @Override
    @Cacheable(value = CacheAbstractionConfiguration.REFERENCE_DATA, key = "'all_patches'")
    public List<Patch> findAll() {
        return patchRepository.findAll();
    }

}
