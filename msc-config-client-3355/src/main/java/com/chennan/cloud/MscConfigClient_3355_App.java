package com.chennan.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot 入口启动类
 *
 * 从服务 msc-config-3344 间接获取git上面的配置项
 *
 * @author chen.nan
 */
@SpringBootApplication
public class MscConfigClient_3355_App {
    public static void main(String[] args) {
        SpringApplication.run(MscConfigClient_3355_App.class, args);
    }
}
