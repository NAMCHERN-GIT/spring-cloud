package com.chennan.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka spring-boot 项目启动入口类
 * @author chen.nan
 */
@SpringBootApplication
@EnableEurekaServer
public class MscEureka_7003_App {
    public static void main(String[] args) {
        SpringApplication.run(MscEureka_7003_App.class, args);
    }
}
