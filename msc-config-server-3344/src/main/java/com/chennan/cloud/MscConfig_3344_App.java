package com.chennan.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Config server 入口启动类
 * {@link @EnableConfigServer} 启动config server配置中心
 *
 * 三种访问形式：
 *  1.http://127.0.0.1:3344/application-dev.yml
 *  2.http://127.0.0.1:3344/application/dev/master
 *  3.http://127.0.0.1:3344/master/application-dev.yml
 *
 * @author chen.nan
 *
 */
@SpringBootApplication
@EnableConfigServer
public class MscConfig_3344_App {
    public static void main(String[] args) {
        SpringApplication.run(MscConfig_3344_App.class, args);
    }
}
