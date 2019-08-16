package com.wangjiangfei.pojo.role;

import com.wangjiangfei.pojo.user.User;

import java.util.List;

/**
 * @author wangjiangfei
 * @date 2019/6/5 8:31
 * @description
 */
public class RoleInfo {

    private Integer roleId;
    private String roleName;

    private List<User> users;
    private List<Menu> menus;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "RoleInfo{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", users=" + users +
                ", menus=" + menus +
                '}';
    }
}
