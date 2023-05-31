package com.example.test.init;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 */
public abstract class BaseHandler {

    private HashMap<MasterTypeEnum, BaseHandler> next = new HashMap<>();

    protected MasterTypeEnum type;

    @Resource
    private HandlerHolder handlerHolder;

    @Resource
    private RedisTemplate redisTemplate;


    @PostConstruct
    public void init() {
        handlerHolder.addHandler(type, this);
        System.out.println(handlerHolder);
    }


    public void test() {

        String orderId = UUID.randomUUID().toString();
        String prefix = "PY_";
        String sourceValue = UUID.randomUUID().toString();

        long source = System.currentTimeMillis();

        Integer count = 5;
        Long time = 60L;

        redisTemplate.opsForZSet().removeRangeByScore(prefix + orderId, 0, time);

        Long size = redisTemplate.opsForZSet().zCard(prefix + orderId);
        if(size == null || size < count) {// 不限流
            redisTemplate.opsForZSet().add(prefix + orderId, sourceValue, source);
            redisTemplate.expire(prefix + orderId, time, TimeUnit.SECONDS);
        }else { //限流

        }


        redisTemplate.executePipelined((RedisCallback<Boolean>) connection -> {
            connection.setEx((prefix + orderId).getBytes(), time, "1".getBytes());
            return true;
        });
    }

}
