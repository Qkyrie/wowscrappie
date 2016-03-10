package com.deswaef.wowscrappie.expansion.service;

import com.deswaef.wowscrappie.expansion.domain.Patch;

import java.util.List;

public interface PatchService {
    List<Patch> findAll();
}
