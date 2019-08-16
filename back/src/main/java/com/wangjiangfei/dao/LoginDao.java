package com.wangjiangfei.dao;

import com.wangjiangfei.pojo.user.User;

/**
 * @author wangjiangfei
 * @date 2019/5/29 10:36
 * @description
 */
public interface LoginDao {

    User selectByLoginName(String userName);
}
