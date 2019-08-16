package com.wangjiangfei.dao;

import com.wangjiangfei.pojo.role.Menu;
import com.wangjiangfei.pojo.role.RoleDetail;
import com.wangjiangfei.pojo.role.RoleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangjiangfei
 * @date 2019/6/5 8:40
 * @description
 */
public interface RoleDao {

    List<RoleInfo> listRole();

    List<Menu> listAllPermission();

    Integer insertRole(RoleDetail roleDetail);

    /**
     * 批量插入角色的权限
     *
     * @param roleId      角色ID
     * @param permissions 权限
     */
    Integer insertRolePermission(@Param("roleId") Integer roleId, @Param("permissions") List<Integer> permissions);

    RoleDetail getRoleAllInfo(Integer roleId);

    Integer updateRoleName(RoleDetail newRole);

    /**
     * 将角色曾经拥有而修改为不再拥有的权限 delete_status改为'2'
     */
    Integer removeOldPermission(@Param("roleId") Integer roleId, @Param("permissions") List<Integer> permissions);

    /**
     * 删除本角色全部权限
     */
    Integer removeRoleAllPermission(Integer roleId);

    Integer removeRole(Integer roleId);

    Integer countByRoleName(String roleName);
}
