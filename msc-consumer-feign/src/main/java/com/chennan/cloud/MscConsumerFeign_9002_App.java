package com.chennan.cloud;

import com.chennan.lb.config.MySelfRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * SpringBoot项目启动入口程序类。
 * {@link @EnableEurekaClient} 开启eureka客户端注册。
 * {@link @RibbonClient} 使用自定义的Ribbon负载均衡策略。
 * @author chen.nan
 */
@SpringBootApplication
@EnableEurekaClient
public class MscConsumerFeign_9002_App {

    public static void main(String[] args) {
        SpringApplication.run(MscConsumerFeign_9002_App.class, args);
    }

}
