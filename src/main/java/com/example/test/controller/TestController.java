package com.example.test.controller;

import com.example.test.service.RedisTestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 */
@RestController
public class TestController {

    @Resource
    private RedisTestService redisTestService;

    @GetMapping("/test")
    public void test() {
        redisTestService.testRedis();
    }


    @GetMapping("/test1")
    public void test1() {
        System.out.println("test1");
    }


    @GetMapping("/test2")
    public String test2() {
        return "test2";
    }
}
