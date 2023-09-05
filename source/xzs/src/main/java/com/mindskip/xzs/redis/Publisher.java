package com.mindskip.xzs.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class Publisher {

    private final RedisTemplate<String, Object> redisTemplate;


    @Autowired
    public Publisher(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void publish(String channel,String message) {
        redisTemplate.convertAndSend(channel, message);
    }
}