package com.chennan.cloud.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 负载均衡策略配置。
 *
 * 配置在客户端
 * 其它微服务如果使用不同的负载均衡策略，需要其微服务项目中自己实现或切换策略；
 * 若所有的微服务都是用同一种策略，则可以在公共模块【msc-common】中定义统一的策略。
 *
 * @author chen.nan
 */
@Configuration
public class LoadBlancedRule {

    /**
     * 自定义负载均衡策略。
     * @return IRule
     */
    @Bean("myRule")
    public IRule myRule(){
        return new RoundRobinRule();
    }

}
