package com.chennan.cloud.web;

import com.chennan.cloud.bo.Dept;
import com.chennan.cloud.service.DeptService;
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

    @Autowired
    DeptService deptService;

    @Autowired
    DiscoveryClient client;

    @PostMapping("/add")
    public int add(Dept dept) {
        return deptService.add(dept);
    }

    @GetMapping("/get")
    public Dept get(Long deptNo) {
        return deptService.get(deptNo);
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
