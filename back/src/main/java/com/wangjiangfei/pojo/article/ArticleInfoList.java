package com.wangjiangfei.pojo.article;

import java.util.List;

/**
 * @author wangjiangfei
 * @date 2019/5/31 8:28
 * @description
 */
public class ArticleInfoList {

    private List<ArticleInfo> articleInfoList;

    private Integer pageNum;
    private Integer pageRow;

    private Integer count;

    public List<ArticleInfo> getArticleInfoList() {
        return articleInfoList;
    }

    public void setArticleInfoList(List<ArticleInfo> articleInfoList) {
        this.articleInfoList = articleInfoList;
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
        return "ArticleInfoList{" +
                "articleInfoList=" + articleInfoList +
                ", pageNum=" + pageNum +
                ", pageRow=" + pageRow +
                ", count=" + count +
                '}';
    }
}
