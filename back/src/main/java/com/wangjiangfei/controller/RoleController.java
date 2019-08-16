package com.wangjiangfei.controller;

import com.wangjiangfei.config.annotation.LoginRequired;
import com.wangjiangfei.config.annotation.RequiresPermission;
import com.wangjiangfei.domain.ServiceVO;
import com.wangjiangfei.pojo.role.Menu;
import com.wangjiangfei.pojo.role.RoleDetail;
import com.wangjiangfei.pojo.role.RoleInfo;
import com.wangjiangfei.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wangjiangfei
 * @date 2019/6/5 8:41
 * @description
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 角色列表
     */
    @LoginRequired
    @RequiresPermission("role:list")
    @GetMapping("/listRole")
    public ServiceVO<List<RoleInfo>> listRole() {
        return roleService.listRole();
    }

    /**
     * 查询所有权限, 给角色分配权限时调用
     */
    @LoginRequired
    @RequiresPermission("role:list")
    @GetMapping("/listAllPermission")
    public ServiceVO<List<Menu>> listAllPermission() {
        return roleService.listAllPermission();
    }

    /**
     * 新增角色
     */
    @LoginRequired
    @RequiresPermission("role:add")
    @PostMapping("/addRole")
    public ServiceVO addRole(@RequestBody RoleDetail roleDetail) {
        return roleService.addRole(roleDetail);
    }

    /**
     * 修改角色
     */
    @LoginRequired
    @RequiresPermission("role:update")
    @PutMapping("/updateRole")
    public ServiceVO updateRole(@RequestBody RoleDetail roleDetail) {
        return roleService.updateRole(roleDetail);
    }

    /**
     * 删除角色
     */
    @LoginRequired
    @RequiresPermission("role:delete")
    @DeleteMapping("/deleteRole/{roleId}")
    public ServiceVO deleteRole(@PathVariable("roleId") Integer roleId) {
        return roleService.deleteRole(roleId);
    }
}
