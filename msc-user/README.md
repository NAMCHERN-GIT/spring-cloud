# 微服务用户模块
开发步骤有以下关键几步接入认证中心
### 1.pom添加依赖
```xml
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-oauth2</artifactId>
    </dependency>
```
### 2.application.yml 配置
```yaml
server:
  port: 5002
spring:
  application:
    name: msc-user

# 注册到注册中心Eureka
eureka:
  instance:
    instance-id: ${spring.application.name}-${server.port}              # 实例别名
    prefer-ip-address: true                                             # 访问路径可以显示ip地址
  client:
    service-url:
      # defaultZone: http://msc-eureka-server:7001/eureka/
      # defaultZone: http://local-eureka-server:7001/eureka/            # Eureka注册中心地址
      # defaultZone: http://msc-eureka-server-1:7001/eureka/,http://msc-eureka-server-2:7002/eureka/,http://msc-eureka-server-3:7003/eureka/
      defaultZone: http://local-eureka-server-1:7001/eureka/,http://local-eureka-server-2:7002/eureka/,http://local-eureka-server-3:7003/eureka/

# 接入微服务认证中心
security:
  oauth2:
    resource:
      id: msc-user                                                      # 资源编号
      user-info-uri: http://localhost:11000/auth/api/userInfo           # 与zuul中的配置相对应
      prefer-token-info: false

```

### 3.其余与普通的开发步骤一样
此处省略。。。