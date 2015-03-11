package com.deswaef.weakauras.classes.service;

import com.deswaef.weakauras.classes.domain.WowClass;
import com.deswaef.weakauras.classes.repository.WowClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private WowClassRepository wowClassRepository;

    @Override
    public Optional<WowClass> bySlug(String slug) {
        return wowClassRepository.findBySlug(slug);
    }

    @Override
    public List<WowClass> findAll() {
        return wowClassRepository.findAll();
    }
}
