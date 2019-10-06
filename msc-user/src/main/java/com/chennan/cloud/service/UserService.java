package com.chennan.cloud.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chennan.cloud.bo.User;
import com.chennan.cloud.dao.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
}
