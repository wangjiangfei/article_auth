package com.wangjiangfei.dao;

import java.util.Set;

/**
 * @author wangjiangfei
 * @date 2019/5/29 14:28
 * @description
 */
public interface PermissionDao {

    Set<String> getMenuListByRoleId(Integer roleId);

    Set<String> getPermissionListByRoleId(Integer roleId);
}
