package com.deswaef.weakauras.classes.service;

import com.deswaef.weakauras.classes.domain.WowClass;

import java.util.List;
import java.util.Optional;

public interface ClassService {

    Optional<WowClass> bySlug(String slug);

    List<WowClass> findAll();

}
