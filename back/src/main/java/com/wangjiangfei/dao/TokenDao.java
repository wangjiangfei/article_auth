package com.wangjiangfei.dao;

import com.wangjiangfei.pojo.user.Token;

/**
 * @author wangjiangfei
 * @date 2019/5/29 10:55
 * @description
 */
public interface TokenDao {

    Token selectByUserId(Integer userId);

    Integer updateByPrimaryKeySelective(Token token);

    Integer insertSelective(Token token);

    Integer getUserIdByTokenName(String tokenName);

    Token selectTokenByTokenName(String tokenName);
}
