package com.deswaef.wowscrappie.websockets.users.repository;

import com.deswaef.wowscrappie.websockets.users.data.ActiveWebSocketUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActiveWebSocketUserRepository {

    public static final String ACTIVE_USERS_CACHE = "CACHE_ACTIVE_USERS";

    @Autowired
    private RedisTemplate<String, ActiveWebSocketUser> redisTemplate;

    public void save(String sessionid, ActiveWebSocketUser activeWebSocketUser) {
        redisTemplate.boundHashOps(ACTIVE_USERS_CACHE).put(sessionid, activeWebSocketUser);
    }

    public void delete(String sessionId) {
        redisTemplate.boundHashOps(ACTIVE_USERS_CACHE).delete(sessionId);
    }

    public Optional<ActiveWebSocketUser> findOne(String sessionId) {
        Object activeUser = redisTemplate.boundHashOps(ACTIVE_USERS_CACHE).get(sessionId);
        if (activeUser != null && activeUser instanceof ActiveWebSocketUser) {
            return Optional.of((ActiveWebSocketUser) activeUser);
        } else {
            return Optional.empty();
        }
    }



}
