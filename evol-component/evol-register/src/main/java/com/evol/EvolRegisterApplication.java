package com.evol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EvolRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvolRegisterApplication.class, args);
    }

}
