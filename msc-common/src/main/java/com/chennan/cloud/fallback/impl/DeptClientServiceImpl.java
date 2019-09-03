package com.chennan.cloud.fallback.impl;

import com.chennan.cloud.bo.Dept;
import com.chennan.cloud.service.DeptClientService;

import java.util.ArrayList;
import java.util.List;

/**
 * Fallback method 实现类
 * @author chen.nan
 */
public class DeptClientServiceImpl implements DeptClientService {

    @Override
    public int add(Dept dept) {
        int a = 88;
        int b = 88;
        return a - b;
    }

    @Override
    public Dept get(Long deptNo) {
        return new Dept()
                .setDeptNo(deptNo)
                .setDeptName("deptNo equal " + deptNo + " is not null")
                .setDbSource("no db_source in mysql");
    }

    @Override
    public List<Dept> list() {
        return new ArrayList<>();
    }

    @Override
    public int edit(Dept dept) {
        int a = 88;
        int b = 88;
        return a - b;
    }

    @Override
    public int delete(Long deptNo) {
        int a = 88;
        int b = 88;
        return a - b;
    }

    private final static String errMsg = "Get discovery info is error";

    @Override
    public String discovery() {
        return errMsg;
    }
}
