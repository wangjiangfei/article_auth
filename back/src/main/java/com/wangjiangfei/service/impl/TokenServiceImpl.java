package com.wangjiangfei.service.impl;

import com.wangjiangfei.dao.TokenDao;
import com.wangjiangfei.pojo.user.Token;
import com.wangjiangfei.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @author wangjiangfei
 * @date 2019/5/29 10:58
 * @description
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenDao tokenDao;

    @Override
    public Token buildToken(int userId, Token token) {
        if(token == null) token = new Token();
        token.setTokenName(UUID.randomUUID().toString());
        token.setCreateTime(new Date());
        token.setUserId(userId);
        token.setEffectiveMinutes(60);
        return token;
    }

    @Override
    public boolean tokenExpired(String tokenName) {
        Token token = tokenDao.selectTokenByTokenName(tokenName);
        if(hasExpired(token)) return true;
        return false;
    }

    @Override
    public boolean hasExpired(Token token) {
        if(token == null || token.getEffectiveMinutes() == null || token.getCreateTime() == null || token.getUserId() == null)
            return true;

        if(new Date().getTime() <= token.getCreateTime().getTime() + token.getEffectiveMinutes()*60*1000){
            //更新token创建时间为当前时间
            token.setCreateTime(new Date());
            tokenDao.updateByPrimaryKeySelective(token);
            return false;
        }
        return true;
    }
}
