package com.chennan.cloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chennan.cloud.bo.Permission;
import com.chennan.cloud.bo.Role;
import com.chennan.cloud.bo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT r.* FROM SYS_USER_ROLE ur INNER JOIN SYS_ROLE r ON ur.ROLE_ID = r.ROLE_ID WHERE ur.USER_ID=#{userId}")
    List<Role> findRoleList(User user);

    @Select("SELECT p.* FROM SYS_ROLE_PERMISSION rp INNER JOIN SYS_PERMISSION p ON RP.PERMISSION_ID = P.PERMISSION_ID WHERE RP.ROLE_ID = #{roleId}")
    List<Permission> findPermission(Role role);
}
