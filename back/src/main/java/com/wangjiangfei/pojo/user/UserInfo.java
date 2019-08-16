package com.wangjiangfei.pojo.user;

import java.util.Date;
import java.util.Set;

/**
 * @author wangjiangfei
 * @date 2019/5/29 10:29
 * @description
 */
public class UserInfo {

    private Integer userId;
    private String userName;
    private String nickname;
    private Integer roleId;
    private String roleName;

    private Date userCreateTime;
    private Date userUpdateTime;

    private Set<String> menuList;
    private Set<String> permissionList;

    private Set<String> permissionNameList;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

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

    public Set<String> getMenuList() {
        return menuList;
    }

    public void setMenuList(Set<String> menuList) {
        this.menuList = menuList;
    }

    public Set<String> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(Set<String> permissionList) {
        this.permissionList = permissionList;
    }

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public Date getUserUpdateTime() {
        return userUpdateTime;
    }

    public void setUserUpdateTime(Date userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }

    public Set<String> getPermissionNameList() {
        return permissionNameList;
    }

    public void setPermissionNameList(Set<String> permissionNameList) {
        this.permissionNameList = permissionNameList;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", userCreateTime=" + userCreateTime +
                ", userUpdateTime=" + userUpdateTime +
                ", menuList=" + menuList +
                ", permissionList=" + permissionList +
                ", permissionNameList=" + permissionNameList +
                '}';
    }
}
