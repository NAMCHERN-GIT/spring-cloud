package com.chennan.cloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 负载均衡策略配置。
 *
 * 配置在客户端
 * 其它微服务如果使用不同的负载均衡策略，需要其微服务项目中自己实现或切换策略；
 * 若所有的微服务都是用同一种策略，则可以在公共模块【msc-common】中定义统一的策略。
 *
 * Spring Cloud 提供的默认策略 {@see RoundRobinRule#choose}
 *
 * @author chen.nan
 */
@Configuration
public class LoadBalancedRuleConfig {

    /**
     * 切换Ribbon负载均衡策略。
     * 使用随机策略代替默认的轮询策略。
     * @return IRule
     */
    @Bean("sysRule")
    public IRule getRule(){
        return new RandomRule();
    }

}
