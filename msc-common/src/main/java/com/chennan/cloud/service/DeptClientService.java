package com.chennan.cloud.service;

import com.chennan.cloud.bo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "msc-provider")
public interface DeptClientService {

    @PostMapping("/dept/add")
    int add(Dept dept);

    /**
     * 2019.09.03 21:06 巨坑，使用feign get方式传单个参数，必须使用 @RequestParam(value = "deptNo") 注解，并且必须声明参数名称
     * @param deptNo 部门编号
     * @return Dept
     */
    @GetMapping(value = "/dept/get")
    Dept get(@RequestParam(value = "deptNo") Long deptNo);

    @GetMapping("/dept/list")
    List<Dept> list();

    @PostMapping("/dept/edit")
    int edit(Dept dept);

    @PostMapping("/dept/delete")
    int delete(@RequestParam(value = "deptNo") Long deptNo);

    @GetMapping("/dept/discovery")
    String discovery();
}
