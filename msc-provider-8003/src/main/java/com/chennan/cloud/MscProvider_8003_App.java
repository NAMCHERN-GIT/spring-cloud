package com.chennan.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient    // 开启eureka客户端注册
@EnableDiscoveryClient // 开启服务发现
public class MscProvider_8003_App {
    public static void main(String[] args) {
        SpringApplication.run(MscProvider_8003_App.class);
    }
}
