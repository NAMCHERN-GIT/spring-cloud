package com.chennan.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 豪猪版dashboard，可以对其他微服务的健康程度进行监控
 * 访问地址：http://xx.xx.xx.xx:10000/hystrix
 *
 * SpringBoot项目启动入口程序类。
 * {@link @EnableHystrixDashboard} 开启 Hystrix Dashboard
 */
@SpringBootApplication
@EnableHystrixDashboard
public class MscHystrixDashboard_10000_App {
    public static void main(String[] args) {
        SpringApplication.run(MscHystrixDashboard_10000_App.class, args);
    }
}
