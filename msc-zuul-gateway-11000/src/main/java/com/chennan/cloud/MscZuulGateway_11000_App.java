package com.chennan.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class MscZuulGateway_11000_App {
    public static void main(String[] args) {
        SpringApplication.run(MscZuulGateway_11000_App.class, args);
    }
}
