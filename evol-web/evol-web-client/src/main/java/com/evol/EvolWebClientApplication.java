package com.evol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class EvolWebClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvolWebClientApplication.class, args);
    }

}
