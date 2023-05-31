//package com.example.test.config;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisClusterConfiguration;
//import org.springframework.data.redis.connection.RedisNode;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.JedisPoolConfig;
//import java.util.HashSet;
//import java.util.Set;
//
//@Configuration
//@Component
//public class RedisConfig {
//    @Value("${spring.redis.cluster.nodes}")
//    private String nodes;
//
//    @Value("${spring.redis.password}")
//    private String password;
//
//    @Value("${spring.redis.jedis.pool.min-idle}")
//    private int minIdle;
//
//    @Value("${spring.redis.jedis.pool.max-active}")
//    private int maxActive;
//
//    @Value("${spring.redis.jedis.pool.max-wait}")
//    private long maxWait;
//
//    @Value("${spring.redis.jedis.pool.max-idle}")
//    private int maxIdle;
//
//
//    //redis配置
//    public JedisPoolConfig poolConfig(){
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMinIdle(minIdle);
//        jedisPoolConfig.setMaxWaitMillis(maxWait);
//        jedisPoolConfig.setMaxTotal(maxActive);
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        return jedisPoolConfig;
//    }
//
//
//    //redis集群配置,6个ip以,分割，然后再以:分割
//    public RedisClusterConfiguration redisClusterConfiguration(){
//        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
//        String[] cNodes = nodes.split(",");
//        Set<RedisNode> hp = new HashSet<>();
//        for (String node : cNodes) {
//            String[] split = node.split(":");
//            hp.add(new RedisNode(split[0].trim(),Integer.valueOf(split[1])));
//        }
//        redisClusterConfiguration.setPassword(password);
//        redisClusterConfiguration.setClusterNodes(hp);
//        return redisClusterConfiguration;
//    }
//
//    //创建redis连接工厂
//    public JedisConnectionFactory jedisConnectionFactory() {
//        //集群模式
//        JedisConnectionFactory  factory = new JedisConnectionFactory(redisClusterConfiguration(),poolConfig());
//        return factory;
//    }
//
//
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        initDomainRedisTemplate(redisTemplate);
//        return redisTemplate;
//    }
//
//    //初始化redis序列化
//    private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
//        //如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        //这个地方有一个问题，这种序列化器会将value序列化成对象存储进redis中，如果
//        //你想取出value，然后进行自增的话，这种序列化器是不可以的，因为对象不能自增；
//        //需要改成StringRedisSerializer序列化器。
//        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
//        redisTemplate.setEnableTransactionSupport(true);
//        redisTemplate.setConnectionFactory(jedisConnectionFactory());
//    }
//
//}