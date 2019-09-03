package com.chennan.cloud.web;

import com.chennan.cloud.bo.Dept;
import com.chennan.cloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 消费端web层。
 * @author chen.nan
 */
@RequestMapping("/dept")
@RestController
public class DeptFeignConsumerController {

    /**
     * 使用Feign，接口+注解的方式，简化client的开发
     */
    @Autowired private DeptClientService clientService;

    @PostMapping("/add")
    public Integer add(Dept dept){
        return clientService.add(dept);
    }

    @GetMapping("/get")
    public Dept get(Long deptNo){
        return clientService.get(deptNo);
    }

    @GetMapping("/list")
    public List list(){
        return clientService.list();
    }

    @PostMapping("/edit")
    public Integer edit(Dept dept){
        return clientService.edit(dept);
    }

    @PostMapping("/delete")
    public Integer delete(Long deptNo){
        return clientService.delete(deptNo);
    }

    @GetMapping("/discovery")
    public String discovery(){
        return clientService.discovery();
    }

}
