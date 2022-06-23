package com.evol;

//import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.spring.web.SpringfoxWebMvcConfiguration;

//@ServletComponentScan
@SpringBootApplication
//@EnableEurekaClient  //nacos 和 eureka 注册中心二选一
@EnableDiscoveryClient
//@EnableFeignClients
//@ConditionalOnClass(CoachOrderServerApplication.class)
@ConditionalOnClass(SpringfoxWebMvcConfiguration.class)
@MapperScan("com.evol.mapper")
//@EnableAutoDataSourceProxy //集成seata
public class EvolOrderServerApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(EvolOrderServerApplication.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
