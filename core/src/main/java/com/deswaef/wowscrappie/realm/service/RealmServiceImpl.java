package com.deswaef.wowscrappie.realm.service;


import com.deswaef.wowscrappie.realm.domain.Locality;
import com.deswaef.wowscrappie.realm.domain.Realm;
import com.deswaef.wowscrappie.realm.repository.RealmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rx.Observable;

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
    public Observable<Realm> findAll() {
        return Observable.from(realmRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Observable<Realm> queryRealms(String query) {
        return Observable.from(realmRepository.findByNameContainingIgnoreCase(query));
    }

}
