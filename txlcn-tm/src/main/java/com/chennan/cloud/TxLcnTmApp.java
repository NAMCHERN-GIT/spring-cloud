package com.chennan.cloud;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring-boot 入口启动类
 * {@link @EnableTransactionManagerServer} 开启事务管理服务
 * @author chen.nan
 */
@SpringBootApplication
@EnableTransactionManagerServer
public class TxLcnTmApp {
    public static void main(String[] args) {
        SpringApplication.run(TxLcnTmApp.class);
    }
}
