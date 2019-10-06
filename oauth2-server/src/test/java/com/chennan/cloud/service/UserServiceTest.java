package com.chennan.cloud.service;


import com.chennan.cloud.bo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired UserService userService;

    @Test
    public void testGetUserInfo(){
        User user = userService.getUserInfo("zhang.san");
        System.out.println(user);
    }

}
