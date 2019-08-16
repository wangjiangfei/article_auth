package com.wangjiangfei.service;

import com.wangjiangfei.domain.ServiceVO;
import com.wangjiangfei.pojo.user.User;
import com.wangjiangfei.pojo.user.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangjiangfei
 * @date 2019/5/29 10:32
 * @description
 */
public interface LoginService {

    ServiceVO authLogin(User user);

    ServiceVO<UserInfo> getInfo(HttpServletRequest request);
}
