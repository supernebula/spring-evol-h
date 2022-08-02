package com.evol.controller;


import com.evol.util.RedisClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private RedisClientUtil redisClientUtil;

    @GetMapping("redis")
    public String redisTest(){

        redisClientUtil.add("test-key1", "test-value1" + System.currentTimeMillis());
        String value = redisClientUtil.getByKey("test-key1");
        return value;
    }

}
