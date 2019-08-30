package com.chennan.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * SpringBoot项目启动入口程序类。
 * @author chen.nan
 */
@SpringBootApplication
@EnableEurekaClient    // 开启eureka客户端注册
public class MscConsumer_9001_App {

    public static void main(String[] args) {
        SpringApplication.run(MscConsumer_9001_App.class, args);
    }

}
