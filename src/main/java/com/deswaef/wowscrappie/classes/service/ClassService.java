package com.deswaef.wowscrappie.classes.service;

import com.deswaef.wowscrappie.classes.domain.WowClass;

import java.util.List;
import java.util.Optional;

public interface ClassService {

    Optional<WowClass> bySlug(String slug);

    List<WowClass> findAll();

}
