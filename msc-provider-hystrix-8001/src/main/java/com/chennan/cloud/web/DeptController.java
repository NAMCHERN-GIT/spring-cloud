package com.chennan.cloud.web;

import com.chennan.cloud.bo.Dept;
import com.chennan.cloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 控制层实现
 * @author chen.nan
 */
@RequestMapping("/dept")
@RestController
public class DeptController {

    @Autowired private DeptService deptService;

    /**
     * 获取服务中的client
     */
    @Autowired private DiscoveryClient client;

    @PostMapping("/add")
    public int add(Dept dept) {
        return deptService.add(dept);
    }

    @GetMapping("/get")
    // 一旦调用服务方法失败并抛出了错误的信息后，会自动调用 @HystrixCommand 标注好的 fallbackMethod 调用类中的指定方法。
    @HystrixCommand(fallbackMethod = "processHandlerGet")
    public Dept get(Long deptNo) {
        Dept dept = deptService.get(deptNo);
        if (dept == null)
            throw new RuntimeException("deptNo equal " + deptNo + " is not null");
        return dept;
    }

    public Dept processHandlerGet(Long deptNo){
        return new Dept()
                .setDeptNo(deptNo)
                .setDeptName("deptNo equal " + deptNo + " is not null")
                .setDbSource("no db_source in mysql");
    }

    @GetMapping("/list")
    public List<Dept> list() {
        return deptService.list();
    }

    @PostMapping("/edit")
    public int edit(Dept dept) {
        return deptService.edit(dept);
    }

    @PostMapping("/delete")
    public int delete(Long deptNo) {
        return deptService.delete(deptNo);
    }

    @GetMapping("/getDiscovery")
    public String getDiscovery(){
        List<String> services = client.getServices();
        services.forEach(System.out::println);
        List<ServiceInstance> serviceInstanceList = client.getInstances("msc-provider");
        return serviceInstanceList.stream().map(instance-> "["
                + instance.getServiceId()
                + "\t" + instance.getHost()
                + "\t" + instance.getPort()
                + "\t" +instance.getUri()
                + "\t" + instance.getInstanceId()
                + "]"
        ).collect(Collectors.joining());
    }

}
