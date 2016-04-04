package com.deswaef.wowscrappie.configuration;

import com.deswaef.wowscrappie.websockets.users.domain.CurrentWebSocketUser;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 43200)
@AutoConfigureAfter(RedisConfiguration.class)
public class RedisHttpSessionConfiguration {

    @Bean
    public RedisTemplate<String, CurrentWebSocketUser> activeUserCache(JedisConnectionFactory connectionFactory) {
        final RedisTemplate<String, CurrentWebSocketUser> activeUserCache = new RedisTemplate<>();
        activeUserCache.setConnectionFactory(connectionFactory);
        activeUserCache.setKeySerializer(new StringRedisSerializer());
        activeUserCache.setHashKeySerializer(new StringRedisSerializer());
        return activeUserCache;
    }

}
