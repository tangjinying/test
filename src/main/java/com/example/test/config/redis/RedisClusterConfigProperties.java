//package com.example.test.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@ConfigurationProperties(prefix = "spring.redis.cluster")
//public class RedisClusterConfigProperties {
//    /**
//     * master nodes
//     */
//    private List<String> nodes;
//    /**
//     * max redirects
//     */
//    private Integer maxRedirects = 3;
//    private String password;
//    public List<String> getNodes() {
//        return nodes;
//    }
//    public void setNodes(List<String> nodes) {
//        this.nodes = nodes;
//    }
//    public Integer getMaxRedirects() {
//        return maxRedirects;
//    }
//    public void setMaxRedirects(Integer maxRedirects) {
//        this.maxRedirects = maxRedirects;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}