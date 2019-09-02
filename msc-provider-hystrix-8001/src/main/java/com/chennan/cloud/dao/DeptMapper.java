package com.chennan.cloud.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chennan.cloud.bo.Dept;
import org.apache.ibatis.annotations.Mapper;

/**
 * mybatis定义dao接口，其实现已经被mybatis接管。
 * @author chen.nan
 */
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {

}
