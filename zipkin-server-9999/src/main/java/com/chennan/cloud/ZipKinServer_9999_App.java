package com.chennan.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * springboot 入口启动程序类
 * {@link @EnableZipkinServer} 标识Zipkin是服务器
 * @author chen.nan
 */
@EnableZipkinServer
@SpringBootApplication
public class ZipKinServer_9999_App {
    public static void main(String[] args) {
        SpringApplication.run(ZipKinServer_9999_App.class, args);
    }
}
