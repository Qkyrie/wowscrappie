package com.deswaef.weakauras.configuration;

import com.deswaef.weakauras.websockets.users.data.ActiveWebSocketUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class RedisSessionConfiguration {

    @Bean
    public JedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, ActiveWebSocketUser> activeUserCache() {
        final RedisTemplate<String, ActiveWebSocketUser> activeUserCache = new RedisTemplate<>();
        activeUserCache.setConnectionFactory(connectionFactory());
        activeUserCache.setKeySerializer(new StringRedisSerializer());
        activeUserCache.setHashKeySerializer(new StringRedisSerializer());
        activeUserCache.setHashValueSerializer(new JacksonJsonRedisSerializer<>(ActiveWebSocketUser.class));
        return activeUserCache;
    }
}
