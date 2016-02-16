package com.deswaef.wowscrappie.realm.service;


import com.deswaef.wowscrappie.realm.domain.Locality;
import com.deswaef.wowscrappie.realm.domain.Realm;
import rx.Observable;

import java.util.List;
import java.util.Optional;

public interface RealmService {
    Optional<Realm> findOne(long realmId);

    void save(List<Realm> resultedRealms);
    Long count();
    List<Realm> findAllByLocality(Locality locality);
    Observable<Realm> findAll();

    Observable<Realm> queryRealms(String query);
}
