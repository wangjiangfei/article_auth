package com.wangjiangfei.service;

import com.wangjiangfei.domain.ServiceVO;
import com.wangjiangfei.pojo.role.RoleInfo;
import com.wangjiangfei.pojo.role.Role;
import com.wangjiangfei.pojo.user.User;
import com.wangjiangfei.pojo.user.UserInfoList;

import java.util.List;

/**
 * @author wangjiangfei
 * @date 2019/6/4 15:17
 * @description
 */
public interface UserService {

    ServiceVO<UserInfoList> listUser(Integer pageNum, Integer pageRow);

    ServiceVO<User> getUserInfoById(Integer userId);

    ServiceVO addUser(User user);

    ServiceVO updateUser(User user);

    /**
     * 查询所有的角色
     * 在添加/修改用户的时候要使用此方法
     * @return
     */
    ServiceVO<List<Role>> getAllRoles();

}
