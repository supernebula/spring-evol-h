package com.evol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
//@EnableEurekaClient  //nacos 和 eureka 注册中心二选一
@EnableDiscoveryClient
@EnableFeignClients
public class EvolGatewayApplication {

    //note
    public static void main(String[] args) {
        SpringApplication.run(EvolGatewayApplication.class, args);
    }



}
