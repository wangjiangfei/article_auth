package com.wangjiangfei.controller;

import com.wangjiangfei.config.annotation.LoginRequired;
import com.wangjiangfei.domain.ServiceVO;
import com.wangjiangfei.pojo.user.User;
import com.wangjiangfei.pojo.user.UserInfo;
import com.wangjiangfei.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangjiangfei
 * @date 2019/5/29 10:19
 * @description 登录
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     */
    @PostMapping("/auth")
    public ServiceVO authLogin(@RequestBody User user) {
        return loginService.authLogin(user);
    }

    /**
     * 查询当前登录用户的信息
     */
    @LoginRequired
    @PostMapping("/getInfo")
    public ServiceVO<UserInfo> getInfo(HttpServletRequest request) {
        return loginService.getInfo(request);
    }

}
