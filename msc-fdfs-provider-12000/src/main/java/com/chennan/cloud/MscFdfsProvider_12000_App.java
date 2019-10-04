package com.chennan.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * spring cloud 整合FastDfs文件服务器实现上传下载功能
 *
 * Spring-boot 入口启动类
 * @author chen.nan
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableCircuitBreaker
public class MscFdfsProvider_12000_App {
    public static void main(String[] args) {
        SpringApplication.run(MscFdfsProvider_12000_App.class, args);
    }
}
