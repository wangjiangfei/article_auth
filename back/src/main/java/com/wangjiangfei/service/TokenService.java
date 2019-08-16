package com.wangjiangfei.service;

import com.wangjiangfei.pojo.user.Token;

/**
 * @author wangjiangfei
 * @date 2019/5/29 10:58
 * @description
 */
public interface TokenService {

    Token buildToken(int userId, Token token);

    boolean tokenExpired(String tokenName);

    boolean hasExpired(Token token);
}
