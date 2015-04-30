package com.deswaef.weakauras.realm.service;


import com.deswaef.weakauras.realm.domain.Locality;
import com.deswaef.weakauras.realm.domain.Realm;
import com.deswaef.weakauras.realm.repository.RealmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RealmServiceImpl implements RealmService {

    @Autowired
    private RealmRepository realmRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Realm> findOne(long realmId) {
        return realmRepository.findOne(realmId);
    }

    @Override
    @Transactional
    public void save(List<Realm> resultedRealms) {
        realmRepository.save(resultedRealms);
    }

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return realmRepository.count();
    }

    @Override
    public List<Realm> findAllByLocality(Locality locality) {
        return realmRepository.findAll()
                .stream()
                .filter(x -> x.getLocality().equals(locality))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Realm> findAll() {
        return realmRepository.findAll();
    }

}
