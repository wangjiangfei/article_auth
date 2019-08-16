package com.wangjiangfei.controller;

import com.wangjiangfei.config.annotation.LoginRequired;
import com.wangjiangfei.config.annotation.RequiresPermission;
import com.wangjiangfei.domain.ServiceVO;
import com.wangjiangfei.pojo.role.Role;
import com.wangjiangfei.pojo.user.User;
import com.wangjiangfei.pojo.user.UserInfoList;
import com.wangjiangfei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wangjiangfei
 * @date 2019/6/4 15:14
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询用户列表
     */
    @LoginRequired
    @RequiresPermission("user:list")
    @GetMapping("/list")
    public ServiceVO<UserInfoList> listUser(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageRow") Integer pageRow) {
        return userService.listUser(pageNum, pageRow);
    }

    /**
     * 查询用户详细信息
     */
    @LoginRequired
    @RequiresPermission("user:list")
    @GetMapping("/detail/{userId}")
    public ServiceVO<User> listUser(@PathVariable("userId") Integer userId) {
        return userService.getUserInfoById(userId);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @LoginRequired
    @RequiresPermission("user:add")
    @PostMapping("/addUser")
    public ServiceVO addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @LoginRequired
    @RequiresPermission("user:update")
    @PutMapping("/updateUser")
    public ServiceVO updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    /**
     * 查询所有的角色
     * 在添加/修改用户的时候要使用此方法
     * @return
     */
    @LoginRequired
    @RequiresPermission("user:add")
    @GetMapping("/getAllRoles")
    public ServiceVO<List<Role>> getAllRoles() {
        return userService.getAllRoles();
    }



}
