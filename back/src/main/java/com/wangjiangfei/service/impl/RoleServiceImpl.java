package com.wangjiangfei.service.impl;

import com.wangjiangfei.dao.RoleDao;
import com.wangjiangfei.domain.ErrorCode;
import com.wangjiangfei.domain.ServiceVO;
import com.wangjiangfei.domain.SuccessCode;
import com.wangjiangfei.pojo.role.Menu;
import com.wangjiangfei.pojo.role.RoleDetail;
import com.wangjiangfei.pojo.role.RoleInfo;
import com.wangjiangfei.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author wangjiangfei
 * @date 2019/6/5 8:42
 * @description
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public ServiceVO<List<RoleInfo>> listRole() {
        List<RoleInfo> roleInfoList = roleDao.listRole();
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, roleInfoList);
    }

    @Override
    public ServiceVO<List<Menu>> listAllPermission() {
        List<Menu> menus = roleDao.listAllPermission();
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, menus);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ServiceVO addRole(RoleDetail roleDetail) {
        int count = roleDao.countByRoleName(roleDetail.getRoleName());
        if (count > 0) {
            return new ServiceVO<>(ErrorCode.ROLE_EXIST_CODE, ErrorCode.ROLE_EXIST_MESS);
        }
        roleDao.insertRole(roleDetail);
        roleDao.insertRolePermission(roleDetail.getRoleId(), roleDetail.getPermissions());
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @Override
    public ServiceVO updateRole(RoleDetail roleDetail) {
        int roleId = roleDetail.getRoleId();
        List<Integer> newPerms = roleDetail.getPermissions();
        RoleDetail roleInfo = roleDao.getRoleAllInfo(roleId);
        List<Integer> oldPerms = roleInfo.getPermissions();
        //修改角色名称
        dealRoleName(roleDetail, roleInfo);
        //添加新权限
        saveNewPermission(roleId, newPerms, oldPerms);
        //移除旧的不再拥有的权限
        removeOldPermission(roleId, newPerms, oldPerms);
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    /**
     * 修改角色名称
     */
    private void dealRoleName(RoleDetail newRole, RoleDetail oldRole) {
        String roleName = newRole.getRoleName();
        if (roleName != null && !roleName.equals(oldRole.getRoleName())) {
            roleDao.updateRoleName(newRole);
        }
    }

    /**
     * 为角色添加新权限
     */
    private void saveNewPermission(Integer roleId, Collection<Integer> newPerms, Collection<Integer> oldPerms) {
        List<Integer> waitInsert = new ArrayList<>();
        for (Integer newPerm : newPerms) {
            if (!oldPerms.contains(newPerm)) {
                waitInsert.add(newPerm);
            }
        }
        if (waitInsert.size() > 0) {
            roleDao.insertRolePermission(roleId, waitInsert);
        }
    }

    /**
     * 删除角色 旧的 不再拥有的权限
     */
    private void removeOldPermission(Integer roleId, Collection<Integer> newPerms, Collection<Integer> oldPerms) {
        List<Integer> waitRemove = new ArrayList<>();
        for (Integer oldPerm : oldPerms) {
            if (!newPerms.contains(oldPerm)) {
                waitRemove.add(oldPerm);
            }
        }
        if (waitRemove.size() > 0) {
            roleDao.removeOldPermission(roleId, waitRemove);
        }
    }

    /**
     * 删除角色
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ServiceVO deleteRole(Integer roleId) {
        RoleDetail roleInfo = roleDao.getRoleAllInfo(roleId);
        List<Integer> users = roleInfo.getUsers();
        if (users != null && users.size() > 0) {
            return new ServiceVO<>(ErrorCode.ROLE_DEL_ERROR_CODE, ErrorCode.ROLE_DEL_ERROR_MESS);
        }
        roleDao.removeRoleAllPermission(roleId);
        roleDao.removeRole(roleId);
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }
}
