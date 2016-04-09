package com.deswaef.wowscrappie.configuration;

import com.deswaef.wowscrappie.repository.IntegrationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;

import static org.assertj.core.api.Assertions.assertThat;

public class CacheTests extends IntegrationTests {

    @Autowired
    private RedisCacheManager cacheManager;

    @Test
    public void cacheManagerExists() throws Exception {
        assertThat(cacheManager).isNotNull();
    }

    @Test
    public void name() throws Exception {
        assertThat(cacheManager.getCacheNames()).containsExactly(
                "bracketscache",
                "amountscache",
                "scrappiereferencedata"
        );
    }
}