package com.chennan.cloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chennan.cloud.bo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
