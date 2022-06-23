package com.evol;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.evol.mapper")
@EnableDiscoveryClient
public class EvolAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvolAuthServerApplication.class, args);
    }

}
