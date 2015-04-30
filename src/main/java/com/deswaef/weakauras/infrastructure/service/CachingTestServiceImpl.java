package com.deswaef.weakauras.infrastructure.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CachingTestServiceImpl implements CachingTestService {
    @Override
    @Cacheable(value = "bracketscache")
    public String cacheThis(long encounterId) {
        return "Hai";
    }
}
