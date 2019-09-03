package com.chennan.cloud.service;

import com.chennan.cloud.bo.Dept;
import com.chennan.cloud.fallback.DeptClientServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Feign 客户端代码 业务逻辑层，接口层，面向接口开发
 * 注解 @FeignClient 中的属性 fallbackFactory实现解耦
 * @author chen.nan
 */
//@FeignClient(name = "msc-provider")
@FeignClient(name = "msc-provider", fallbackFactory = DeptClientServiceFallback.class)
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
