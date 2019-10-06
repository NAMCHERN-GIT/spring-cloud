package com.chennan.cloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chennan.cloud.bo.Permission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
}
