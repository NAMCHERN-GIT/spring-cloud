package com.chennan.cloud.web;


import com.chennan.cloud.base.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RequestMapping("/api")
@RestController
public class UserController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("/userInfo")
    public Principal user(Principal userInfo) {
        return userInfo;
    }

    @DeleteMapping(value = "/exit")
    public R revokeToken(String access_token) {
        if (!consumerTokenServices.revokeToken(access_token))
            return R.err("注销失败");
        return R.ok();
    }

}
