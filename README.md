# spring-cloud
spring-cloud学习

学习初始阶段阶段

## 环境配置
### mysql数据库配置
```text
导入spring-cloud项目下db文件夹的SQL脚本。
```

### 模拟多台主机（伪分布式）
```text
修改所在主机的hosts文件，追加config目录下hosts文件中的内容到所在主机的hosts文件。
```

### githup配置中心地址
[https://github.com/NAMCHERN-GIT/spring-cloud-config/blob/master/application.yml](https://github.com/NAMCHERN-GIT/spring-cloud-config/blob/master/application.yml)  
[https://github.com/NAMCHERN-GIT/spring-cloud-config/blob/master/msc-config-client.yml](https://github.com/NAMCHERN-GIT/spring-cloud-config/blob/master/msc-config-client.yml)
### 踩坑1
```java
@FeignClient(name = "msc-provider")
public interface DeptClientService {                                    
    /**
     * 2019.09.03 21:06 巨坑，使用feign get方式传单个参数，必须使用 @RequestParam(value = "deptNo") 注解，并且必须声明参数名称
     * @param deptNo 部门编号
     * @return Dept
     */
    @GetMapping(value = "/dept/get")
    Dept get(@RequestParam(value = "deptNo") Long deptNo);
}
```

### 踩坑2
zuul服务网关代理配置的坑
```yaml
# 服务网关代理
zuul:
  # 踩坑：prefix配置项前必须有'/',否则GG
  # 访问地址：http://127.0.0.1:11000/chennan/myProvider/dept/get?deptNo=1
  prefix: /chennan
  ignored-services: "*"
  routes:
    myProvider.serviceId: msc-provider
    myProvider.path: /myProvider/**
```

### 踩坑3
注解 @RefreshScope 作用是刷新github上的配置，该注解必须放在@Controller 注解标注的类上，否则GG
```java
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

```
使用post方式手动刷新配置  
```shell script
curl -XPOST http://127.0.0.1:3355/actuator/refresh
```
控制台内容
```text
["config.client.version","spring.application.name"]
```

### 踩坑4  spring-cloud-stream rabbit 避免重读消费
```yaml
spring:
    cloud:
        stream:
          bindings:
            # 通过 spring.cloud.stream.bindings.*.destination 的配置，让输入通道和输出通道对应到同一个主题上
            myInput:
              # 指定输入通道对应的主题名
              destination: minestream
              # 指定该应用实例属于 stream 消费组，为了避免重复消费的问题，加入了消费组
              group: stream
            myOutput:
              destination: minestream
```
### 踩坑4:spring-cloud-bus 消息总线通知刷新接口
```text
POST /actuator/bus-refresh
```

### TX-LCN 分布式事务解决
[请阅读txlcn-tm](txlcn-tm/README.md)

### 认证与授权
