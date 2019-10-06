package com.chennan.cloud.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chennan.cloud.bo.Role;
import com.chennan.cloud.bo.User;
import com.chennan.cloud.dao.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户角色权限查询业务类实现
 * @author chen.nan
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    public User getByUserName(String userName){
        return getOne(new QueryWrapper<User>().eq("USER_NAME", userName), true);
    }

    public User getUserInfo(String userName){
        User userInfo = getByUserName(userName);
        List<Role> roleList = baseMapper.findRoleList(userInfo);
        roleList.forEach(role->role.setPermissionList(baseMapper.findPermission(role)));
        return userInfo.setRoleList(roleList);
    }

}
