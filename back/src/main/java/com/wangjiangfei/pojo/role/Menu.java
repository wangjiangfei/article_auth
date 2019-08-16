package com.wangjiangfei.pojo.role;

import com.wangjiangfei.pojo.user.Permission;

import java.util.List;

/**
 * @author wangjiangfei
 * @date 2019/6/5 8:32
 * @description
 */
public class Menu {

    private String menuCode;
    private String menuName;

    private List<Permission> permissions;

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuCode='" + menuCode + '\'' +
                ", menuName='" + menuName + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
