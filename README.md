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
