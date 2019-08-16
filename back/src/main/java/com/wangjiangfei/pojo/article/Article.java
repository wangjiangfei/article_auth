package com.wangjiangfei.pojo.article;

import java.util.Date;

/**
 * @author wangjiangfei
 * @date 2019/5/30 15:51
 * @description
 */
public class Article {

    private Integer articleId;
    private String content;
    private Date createTime;
    private Date updateTime;
    private String deleteStatus;
    private Integer createUserId;
    private Integer updateUserId;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleteStatus='" + deleteStatus + '\'' +
                ", createUserId=" + createUserId +
                ", updateUserId=" + updateUserId +
                '}';
    }
}
