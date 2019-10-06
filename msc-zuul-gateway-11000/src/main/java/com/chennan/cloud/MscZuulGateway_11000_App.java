package com.chennan.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * springboot 入口启动类
 * {@link @EnableZuulProxy} 网关代理enable
 *
 * @author chen.nan
 */
@SpringBootApplication
@EnableZuulProxy
@EnableOAuth2Sso
@EnableDiscoveryClient
public class MscZuulGateway_11000_App {
    public static void main(String[] args) {
        SpringApplication.run(MscZuulGateway_11000_App.class, args);
    }
}
