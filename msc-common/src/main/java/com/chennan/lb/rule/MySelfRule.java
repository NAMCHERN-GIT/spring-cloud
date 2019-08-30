package com.chennan.lb.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * 每个微服务提供者每5次轮询一下策略
 * @author chen.nan
 */
public class MySelfRule extends AbstractLoadBalancerRule {

    /**
     * 总共被调用的次数
     */
    private int total = 0;

    /**
     * 当前提供服务的微服务序号
     */
    private int currentIndex = 0;

    private Server choose(ILoadBalancer lb) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted())  return null;

            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) return null;

            if (total < 5){
                server = upList.get(currentIndex);
                total ++ ;
            }else {
                total = 0;
                currentIndex ++ ;
                if (currentIndex >= upList.size())currentIndex = 0;
            }

            if (server == null) {
                Thread.yield();
                continue;
            }

            if (server.isAlive()) return (server);

            server = null;
            Thread.yield();
        }
        return server;
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer());
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }
}
