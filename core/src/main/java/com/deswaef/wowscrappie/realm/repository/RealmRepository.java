package com.deswaef.wowscrappie.realm.repository;


import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.realm.domain.Locality;
import com.deswaef.wowscrappie.realm.domain.Realm;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RealmRepository extends JpaRepository<Realm, Long> {
    Optional<Realm> findByNameAndLocality(@Param("name") String name, @Param("locality") Locality locality);

    Optional<Realm> findBySlugAndLocality(@Param("slug") String slug, @Param("locality") Locality locality);

    List<Realm> findByNameContainingIgnoreCase(@Param("name") String name);
}
