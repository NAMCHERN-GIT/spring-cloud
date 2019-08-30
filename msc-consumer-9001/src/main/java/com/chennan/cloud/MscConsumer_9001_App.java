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
@RibbonClient(name = "msc-provider", configuration = MySelfRuleConfig.class)
public class MscConsumer_9001_App {

    public static void main(String[] args) {
        SpringApplication.run(MscConsumer_9001_App.class, args);
    }

}
