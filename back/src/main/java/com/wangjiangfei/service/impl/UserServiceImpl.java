package com.wangjiangfei.service.impl;

import com.wangjiangfei.dao.UserDao;
import com.wangjiangfei.domain.ErrorCode;
import com.wangjiangfei.domain.ServiceVO;
import com.wangjiangfei.domain.SuccessCode;
import com.wangjiangfei.pojo.role.RoleInfo;
import com.wangjiangfei.pojo.role.Role;
import com.wangjiangfei.pojo.user.User;
import com.wangjiangfei.pojo.user.UserInfo;
import com.wangjiangfei.pojo.user.UserInfoList;
import com.wangjiangfei.service.UserService;
import com.wangjiangfei.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangjiangfei
 * @date 2019/6/4 15:19
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public ServiceVO<UserInfoList> listUser(Integer pageNum, Integer pageRow) {
        UserInfoList infoList = new UserInfoList();
        int count = userDao.countUser();
        pageNum = pageNum == 0 ? 1 : pageNum;
        int offSet = (pageNum - 1) * pageRow;
        List<UserInfo> infos = userDao.listUser(offSet, pageRow);

        infoList.setUserInfoList(infos);
        infoList.setPageNum(pageNum);
        infoList.setPageRow(pageRow);
        infoList.setCount(count);
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, infoList);
    }

    @Override
    public ServiceVO<User> getUserInfoById(Integer userId) {
        User user = userDao.getUserInfoById(userId);
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, user);
    }

    @Override
    public ServiceVO addUser(User user) {
        int exist = userDao.queryExistUsername(user.getUserName());
        if (exist > 0) {
            return new ServiceVO<>(ErrorCode.ACCOUNT_EXIST_CODE, ErrorCode.ACCOUNT_EXIST_MESS);
        }
        user.setPassword(MD5Util.md5Encode(user.getPassword()));
        userDao.addUser(user);
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @Override
    public ServiceVO updateUser(User user) {
        String password = user.getPassword();
        if (password != null && !password.equals("")) {
            user.setPassword(MD5Util.md5Encode(password));
        }
        userDao.updateUser(user);
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @Override
    public ServiceVO<List<Role>> getAllRoles() {
        List<Role> roles = userDao.getAllRoles();
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, roles);
    }


}
