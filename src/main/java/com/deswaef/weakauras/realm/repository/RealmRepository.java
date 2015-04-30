package com.deswaef.weakauras.realm.repository;


import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.realm.domain.Locality;
import com.deswaef.weakauras.realm.domain.Realm;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RealmRepository extends JpaRepository<Realm, Long> {
    Optional<Realm> findByNameAndLocality(@Param("name") String name, @Param("locality") Locality locality);
    Optional<Realm> findBySlugAndLocality(@Param("slug") String slug, @Param("locality") Locality locality);
}
