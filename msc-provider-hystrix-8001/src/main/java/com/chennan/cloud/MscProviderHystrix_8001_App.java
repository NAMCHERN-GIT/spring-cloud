package com.chennan.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 服务提供者 Spring-boot 入口启动类
 * @author chen.nan
 */
@SpringBootApplication // SpringBoot应用启动标识
@EnableEurekaClient    // 开启eureka客户端注册
@EnableDiscoveryClient // 开启服务发现
@EnableCircuitBreaker  // 对hystrixR熔断机制的支持
public class MscProviderHystrix_8001_App {

    public static void main(String[] args) {
        SpringApplication.run(MscProviderHystrix_8001_App.class, args);
    }

}
