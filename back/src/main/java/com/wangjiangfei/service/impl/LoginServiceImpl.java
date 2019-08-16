package com.wangjiangfei.service.impl;

import com.wangjiangfei.dao.LoginDao;
import com.wangjiangfei.dao.PermissionDao;
import com.wangjiangfei.dao.TokenDao;
import com.wangjiangfei.dao.UserDao;
import com.wangjiangfei.domain.ErrorCode;
import com.wangjiangfei.domain.ServiceVO;
import com.wangjiangfei.domain.SuccessCode;
import com.wangjiangfei.pojo.user.Token;
import com.wangjiangfei.pojo.user.User;
import com.wangjiangfei.pojo.user.UserInfo;
import com.wangjiangfei.service.LoginService;
import com.wangjiangfei.service.TokenService;
import com.wangjiangfei.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @author wangjiangfei
 * @date 2019/5/29 10:34
 * @description
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;
    @Autowired
    private TokenDao tokenDao;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public ServiceVO authLogin(User user) {
        User temp = loginDao.selectByLoginName(user.getUserName());
        if(temp != null && MD5Util.md5Encode(user.getPassword()).equals(temp.getPassword())){
            Token token = tokenDao.selectByUserId(temp.getUserId());
            token = tokenService.buildToken(temp.getUserId(),token);
            try {
                if (token.getTokenId() == null) {
                    tokenDao.insertSelective(token);
                } else {
                    tokenDao.updateByPrimaryKeySelective(token);
                }
                return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, token);
            } catch (Exception e) {
                //数据库存储失败
                System.out.println(e);
                return new ServiceVO(ErrorCode.DATA_SAVE_ERR_CODE, ErrorCode.DATA_SAVE_ERR_MESS);
            }

        }
        //用户名、密码错误
        return new ServiceVO(ErrorCode.NAME_PASSWORD_ERROR_CODE, ErrorCode.NAME_PASSWORD_ERROR_MESS);
    }

    @Override
    public ServiceVO<UserInfo> getInfo(HttpServletRequest request) {
        int userId = tokenDao.getUserIdByTokenName(request.getHeader("token"));
        UserInfo userInfo = userDao.getUserInfoByUserId(userId);
        Set<String> menuList = permissionDao.getMenuListByRoleId(userInfo.getRoleId());
        Set<String> permissionList = permissionDao.getPermissionListByRoleId(userInfo.getRoleId());
        userInfo.setMenuList(menuList);
        userInfo.setPermissionList(permissionList);
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, userInfo);
    }
}
