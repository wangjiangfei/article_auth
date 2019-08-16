package com.wangjiangfei.dao;

import com.wangjiangfei.pojo.role.Role;
import com.wangjiangfei.pojo.user.User;
import com.wangjiangfei.pojo.user.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangjiangfei
 * @date 2019/5/29 14:23
 * @description
 */
public interface UserDao {

    UserInfo getUserInfoByUserId(Integer userId);

    User getUserInfoById(Integer userId);

    Integer countUser();

    List<UserInfo> listUser(@Param("offSet") Integer offSet, @Param("pageRow") Integer pageRow);

    Integer queryExistUsername(String userName);

    Integer addUser(User user);

    Integer updateUser(User user);

    List<Role> getAllRoles();
}
