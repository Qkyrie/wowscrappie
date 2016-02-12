package com.deswaef.wowscrappie.websockets.users.repository;

import com.deswaef.wowscrappie.websockets.users.domain.CurrentWebSocketUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActiveWebSocketUserRepository {

    public static final String ACTIVE_USERS_CACHE = "CACHE_ACTIVE_USERS";

    @Autowired
    private RedisTemplate<String, CurrentWebSocketUser> redisTemplate;

    public void save(String sessionid, CurrentWebSocketUser currentWebSocketUser) {
        redisTemplate.boundHashOps(ACTIVE_USERS_CACHE).put(sessionid, currentWebSocketUser);
    }

    public void delete(String sessionId) {
        redisTemplate.boundHashOps(ACTIVE_USERS_CACHE).delete(sessionId);
    }

    public Optional<CurrentWebSocketUser> findOne(String sessionId) {
        Object activeUser = redisTemplate.boundHashOps(ACTIVE_USERS_CACHE).get(sessionId);
        if (activeUser != null && activeUser instanceof CurrentWebSocketUser) {
            return Optional.of((CurrentWebSocketUser) activeUser);
        } else {
            return Optional.empty();
        }
    }



}
