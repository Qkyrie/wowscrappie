package com.deswaef.wowscrappie.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Arrays;

import static java.lang.String.*;

@EnableCaching
@Configuration
public class CacheAbstractionConfiguration {

    public static final String BRACKETSCACHE = "bracketscache";
    public static final String AMOUNTS_CACHE = "amountscache";
    public static final String REFERENCE_DATA = "scrappiereferencedata";
    private final RedisSerializer serializer = new StringRedisSerializer();

    @Bean
    RedisTemplate<Object, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public RedisCacheManager redisCacheManager(JedisConnectionFactory jedisConnectionFactory) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate(jedisConnectionFactory), Arrays.asList(BRACKETSCACHE, AMOUNTS_CACHE, REFERENCE_DATA));
        redisCacheManager.setUsePrefix(true);
        redisCacheManager.setCachePrefix(cacheName -> serializer.serialize((format("scrappiecache.%s:", cacheName))));
        redisCacheManager.setDefaultExpiration(300);
        return redisCacheManager;
    }
}
