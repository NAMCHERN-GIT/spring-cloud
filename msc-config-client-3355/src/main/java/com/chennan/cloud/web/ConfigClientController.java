package com.chennan.cloud.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试从github上面获取配置
 * {@link @RefreshScope} 在手动执行刷新的时候会更新变量,/actuator/refresh提供了手动刷新的功能，并且必须使用POST方式；
 * @author chen.nan
 */
@RefreshScope
@RequestMapping("/config")
@RestController
@Slf4j
public class ConfigClientController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${eureka.client.service-url.defaultZone}")
    private String eurekaServers;

    @Value("${server.port}")
    private String port;

    @GetMapping("/getConfig")
    public String getConfig(){
        String result = "application: " + applicationName
                + "\t " + "eurekaServers: " + eurekaServers
                + "\t " + "port: " + port;
        log.info(result);
        return result;
    }
}
