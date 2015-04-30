package com.deswaef.weakauras.realm.service;


import com.deswaef.weakauras.realm.domain.Locality;
import com.deswaef.weakauras.realm.domain.Realm;

import java.util.List;
import java.util.Optional;

public interface RealmService {
    Optional<Realm> findOne(long realmId);

    void save(List<Realm> resultedRealms);
    Long count();
    List<Realm> findAllByLocality(Locality locality);
    List<Realm> findAll();
}
