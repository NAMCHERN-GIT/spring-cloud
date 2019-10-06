package com.chennan.cloud.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chennan.cloud.bo.Role;
import com.chennan.cloud.dao.RoleMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {
}
