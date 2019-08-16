package com.wangjiangfei.pojo.user;

import java.util.Date;

/**
 * @author wangjiangfei
 * @date 2019/5/29 10:12
 * @description
 */
public class Token {

    private Integer tokenId;
    private String tokenName;
    private Date createTime;
    private Integer effectiveMinutes;
    private Integer userId;

    public Integer getTokenId() {
        return tokenId;
    }

    public void setTokenId(Integer tokenId) {
        this.tokenId = tokenId;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getEffectiveMinutes() {
        return effectiveMinutes;
    }

    public void setEffectiveMinutes(Integer effectiveMinutes) {
        this.effectiveMinutes = effectiveMinutes;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenId=" + tokenId +
                ", tokenName='" + tokenName + '\'' +
                ", createTime=" + createTime +
                ", effectiveMinutes=" + effectiveMinutes +
                ", userId=" + userId +
                '}';
    }
}
