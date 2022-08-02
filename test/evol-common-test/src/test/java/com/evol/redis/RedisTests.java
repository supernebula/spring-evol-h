package com.evol.redis;

import com.evol.EvolCommonTestApplication;
import com.evol.util.RedisClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EvolCommonTestApplication.class)
@Slf4j
public class RedisTests {

    @Autowired
    private ApplicationContext applicationContext;

    private static Map<String, List<Integer>> roleMap;

    private RedisClientUtil redisClientUtil;


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Test
    public void contextLoads() {
        System.out.println(applicationContext.getBeanDefinitionCount());
        Stream.of(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }

    @Before
    public void init(){

        redisClientUtil = applicationContext.getBean(RedisClientUtil.class);

        roleMap = new HashMap<>();
        Random random = new Random();
        for (int j = 0; j < 1000; j++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int num = 1; num < 100; num++) {
                list.add(random.nextInt(10000));
            }

            roleMap.put(j + "", list.stream().collect(Collectors.toList()));
        }

        roleMap.forEach((i, item) -> {
            redisClientUtil.hmSet3("station-perm",  i, item);
        });
    }


    @Test
    public void Test(){

        Object obj = redisClientUtil.hmGet3("station-perm",  "2");
        ArrayList<Integer> arrayList = (ArrayList<Integer>)obj;
        log.info("station-permis-arrayList.size:" + arrayList.size());
    }
}
