package com.chennan.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * springboot 入口启动类
 * {@link @EnableZuulProxy} 网关代理enable
 *
 * @author chen.nan
 */
@SpringBootApplication
@EnableZuulProxy
public class MscZuulGateway_11000_App {
    public static void main(String[] args) {
        SpringApplication.run(MscZuulGateway_11000_App.class, args);
    }
}
