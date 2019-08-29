package com.chennan.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * restTemplate 加入IOC容器
 * @author chen.nan
 */
@Configuration
public class RestTemplateConfig {

    /**
     * Spring Cloud Ribbon是基于Netflix Ribbon 实现的 一套客户端负载均衡 工具
     * @return
     */
    @Bean
    @LoadBalanced   // 获得RestTemplate时加入Ribbon的配置
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
