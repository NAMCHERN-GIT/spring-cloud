package com.chennan.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * SpringBoot项目启动入口程序类。
 * {@link @EnableEurekaClient} 开启eureka客户端注册。
 * {@link @EnableFeignClients} 开启Feign客户端注册。
 * @author chen.nan
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class MscConsumerFeign_9002_App {

    public static void main(String[] args) {
        SpringApplication.run(MscConsumerFeign_9002_App.class, args);
    }

}
