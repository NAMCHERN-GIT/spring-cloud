package com.chennan.cloud.web;

import com.chennan.cloud.base.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * web restful层接口实现
 * @author chen.nan
 */
@RequestMapping("/api")
@RestController
public class UserController {

    @Autowired private ConsumerTokenServices consumerTokenServices;

    /**
     * 用户认证信息查询
     */
    @GetMapping("/userInfo")
    public Principal user(Principal userInfo) {
        return userInfo;
    }

    /**
     * 退出，销毁access_token
     */
    @PostMapping(value = "/exit")
    public R revokeToken(String access_token) {
        if (!consumerTokenServices.revokeToken(access_token))
            return R.err("注销失败");
        return R.ok();
    }

}
