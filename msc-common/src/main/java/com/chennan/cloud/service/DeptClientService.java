package com.chennan.cloud.service;

import com.chennan.cloud.bo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "msc-provider")
public interface DeptClientService {

    @PostMapping("/dept/add")
    int add(Dept dept);

    @GetMapping("/dept/get")
    Dept get(Long deptNo);

    @GetMapping("/dept/list")
    List<Dept> list();

    @PostMapping("/dept/edit")
    int edit(Dept dept);

    @PostMapping("/dept/delete")
    int delete(Long deptNo);

    @GetMapping("/dept/discovery")
    String discovery();
}
