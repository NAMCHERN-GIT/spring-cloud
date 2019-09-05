package com.chennan.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 豪猪版dashboard，可以对其他微服务的健康程度进行监控
 * 访问地址：http://xx.xx.xx.xx:10000/hystrix
 *
 * SpringBoot项目启动入口程序类。
 * {@link @EnableHystrixDashboard} 启用 HystrixDashboard 断路器看板 相关配置
 * {@link @EnableDiscoveryClient} 启用 Eureka 服务发现 相关配置
 */
@SpringBootApplication
@EnableHystrixDashboard
@EnableDiscoveryClient
public class MscHystrixDashboard_10000_App {
    public static void main(String[] args) {
        SpringApplication.run(MscHystrixDashboard_10000_App.class, args);
    }
}
