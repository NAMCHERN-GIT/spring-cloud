package com.chennan.lb.config;

import com.chennan.lb.rule.MySelfRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义的策略类不能放在 @ComponentScan所在包以及子包下
 * @author chen.nan
 */
@Configuration
public class MySelfRuleConfig {

    /**
     * 使用自定义轮询策略（每个微服务提供者轮询5次才切换下一个微服务）
     * @return IRule
     */
    @Bean
    public IRule getRule(){
        return new MySelfRule();
    }

}
