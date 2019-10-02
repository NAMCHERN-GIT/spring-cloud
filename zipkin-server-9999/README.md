## Zipkin
&emsp;&emsp;Zipkin是Twitter的一个开源项目，它基于Google Dapper实现，它致力于收集服务的定时数据，以解决微服务架构中的延迟问题，包括数据的收集、存
储、查找和展现。

## Zipkin原理
&emsp;&emsp;基本思路是在服务调用的请求和响应中加入ID，标明上下游请求的关系。利用这些信息，可以可视化地分析服务调用链路和服务间的依赖关系。

## 数据存储方式
&emsp;&emsp;Zipkin提供了可插拔数据存储方式：  
* In-Memory（默认）
* MySql
* Cassandra 
* Elasticsearch

## 核心组件
* Collector：收集器组件，它主要用于处理从外部系统发送过来的跟踪信息，将这些信息转换为Zipkin内部处理的Span格式，以支持后续的存储、分析、展示等功能。
* Storage：存储组件，它主要对处理收集器接收到的跟踪信息，默认会将这些信息存储在内存中，我们也可以修改此存储策略，通过使用其他存储组件将跟踪信息存储到数据库中。
* RESTful API：API组件，它主要用来提供外部访问接口。比如给客户端展示跟踪信息，或是外接系统访问以实现监控等。
* Web UI：UI组件，基于API组件实现的上层应用。通过UI组件用户可以方便而又直观地查询和分析跟踪信息。

## 应用
&emsp;&emsp;Zipkin分为两端，一个是Zipkin服务端，一个是Zipkin客户端，客户端也就是微服务应用。客户端会配置服务端的URL地址，一旦发生服务间的调用时，会被配置在
微服务里面的Sleuth监听器监听，并生成相应的Trace和Span信息发送给服务端。发送的方式主要有两种，一种是HTTP报文的方式，另一种是消息总线的方式如RabbitMQ。

## zipkin服务端配置
* pom 文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-cloud</artifactId>
        <groupId>com.chennan.cloud</groupId>
        <version>1.0.0</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>zipkin-server-9999</artifactId>
    <description>zipkin服务中心</description>
    <version>1.0.0</version>

    <properties>
        <java.version>1.8</java.version>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <zipkin.version>2.12.9</zipkin.version>
    </properties>

    <dependencies>
        <!-- 引入jetty作为内嵌的web容器 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jetty</artifactId>
        </dependency>
        <!-- 引入 zipkin-server 依赖 -->
        <dependency>
            <groupId>io.zipkin.java</groupId>
            <artifactId>zipkin-server</artifactId>
            <version>${zipkin.version}</version>
        </dependency>
        <!-- 引入zipkin autoconfigure-ui 依赖 -->
        <dependency>
            <groupId>io.zipkin.java</groupId>
            <artifactId>zipkin-autoconfigure-ui</artifactId>
            <version>${zipkin.version}</version>
        </dependency>
    </dependencies>
</project>
```
* application.yml 文件
```yaml
# 设置zipkin访问端口协议
armeria:
  ports:
    - port: 9999
      protocols:
        - http

# 必须设置属性 server.compression.enabled = true
server:
  compression:
    enabled: true

# 必须设置属性 spring.main.web-application-type = none
# 参考 See https://blog.csdn.net/chenglu6516/article/details/100698050
# 参考 See https://docs.spring.io/spring-boot/docs/current/reference/html/howto-embedded-web-servers.html.
# 参考 See https://github.com/line/armeria-examples/blob/master/spring-boot-minimal/src/main/resources/config/application.yml
spring:
  application:
    name: zipkin-server
  main:
    web-application-type: none
```
* springboot 主启动类
```java
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
```

## Zipkin客户端配置
* pom 加入依赖
```xml
<!-- zipkin 客户端 依赖引入 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-sleuth-zipkin</artifactId>
</dependency>
```
* application.yml 加入配置项
```yaml
spring:
  zipkin:
    base-url: http://localhost:9999
  sleuth:
    sampler:
      probability: 1.0
```

# 踩坑
## 坑1
```text
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'armeriaServer'
```
#### 解决方案
参考  [Spring Cloud2 Zipkin集成之异常排错记](https://my.oschina.net/u/875122/blog/3094580)    
参考  [armeria-examples](https://github.com/line/armeria-examples/blob/master/spring-boot-minimal/src/main/resources/config/application.yml)  
参考  [spring官网](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-embedded-web-servers.html)
```yaml
server:
  compression:
    enabled: true

spring:
  main:
    web-application-type: none
```

