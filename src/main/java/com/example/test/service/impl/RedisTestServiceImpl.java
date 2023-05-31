package com.example.test.service.impl;

import com.example.test.service.RedisTestService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 */
@Service
public class RedisTestServiceImpl implements RedisTestService {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void testRedis() {
        Object key1 = redisTemplate.opsForValue().get("aaa");
        if(key1 == null) {
            redisTemplate.opsForValue().set("aaa", "v1");
        }
        System.out.println(key1);
    }
}
