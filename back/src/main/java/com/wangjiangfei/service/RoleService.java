package com.wangjiangfei.service;

import com.wangjiangfei.domain.ServiceVO;
import com.wangjiangfei.pojo.role.Menu;
import com.wangjiangfei.pojo.role.RoleDetail;
import com.wangjiangfei.pojo.role.RoleInfo;

import java.util.List;

/**
 * @author wangjiangfei
 * @date 2019/6/5 8:42
 * @description
 */
public interface RoleService {

    ServiceVO<List<RoleInfo>> listRole();

    ServiceVO<List<Menu>> listAllPermission();

    ServiceVO addRole(RoleDetail roleDetail);

    ServiceVO updateRole(RoleDetail roleDetail);

    ServiceVO deleteRole(Integer roleId);
}
