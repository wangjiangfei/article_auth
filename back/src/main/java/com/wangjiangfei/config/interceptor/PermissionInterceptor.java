package com.wangjiangfei.config.interceptor;

import com.wangjiangfei.config.annotation.RequiresPermission;
import com.wangjiangfei.config.exception.NoPermissionException;
import com.wangjiangfei.dao.TokenDao;
import com.wangjiangfei.domain.ServiceVO;
import com.wangjiangfei.pojo.user.UserInfo;
import com.wangjiangfei.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author wangjiangfei
 * @date 2019/5/30 15:20
 * @description 权限拦截器
 */
public class PermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenDao tokenDao;
    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 判断接口是否需要验证权限
        RequiresPermission methodAnnotation = method.getAnnotation(RequiresPermission.class);
        // 有 @RequiresPermission 注解，需要验证权限
        if (methodAnnotation != null) {
            // 验证权限
            ServiceVO<UserInfo> userInfo = loginService.getInfo(request);
            Set<String> set = userInfo.getInfo().getPermissionList();
            // 遍历该用户的所有权限
            for (String s : set) {
                if (s.equals(methodAnnotation.value())) {
                    return true;
                }
            }
            throw new NoPermissionException();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
