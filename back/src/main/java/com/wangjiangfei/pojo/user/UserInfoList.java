package com.wangjiangfei.pojo.user;

import java.util.List;

/**
 * @author wangjiangfei
 * @date 2019/6/4 15:12
 * @description
 */
public class UserInfoList {

    private List<UserInfo> userInfoList;

    private Integer pageNum;
    private Integer pageRow;

    private Integer count;

    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageRow() {
        return pageRow;
    }

    public void setPageRow(Integer pageRow) {
        this.pageRow = pageRow;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "UserInfoList{" +
                "userInfoList=" + userInfoList +
                ", pageNum=" + pageNum +
                ", pageRow=" + pageRow +
                ", count=" + count +
                '}';
    }
}
