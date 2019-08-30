package com.chennan.cloud.service;


import com.chennan.cloud.bo.Dept;
import com.chennan.cloud.dao.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层实现类
 * @author chen.nan
 */
@Service
public class DeptService {

    @Autowired
    DeptMapper deptMapper;

    public int add(Dept dept) {
        return deptMapper.insert(dept);
    }

    public Dept get(Long deptNo) {
        return deptMapper.selectById(deptNo);
    }

    public List<Dept> list() {
        return deptMapper.selectList(null);
    }

    public int edit(Dept dept) {
        return deptMapper.updateById(dept);
    }

    public int delete(Long deptNo) {
        return deptMapper.deleteById(deptNo);
    }

}
