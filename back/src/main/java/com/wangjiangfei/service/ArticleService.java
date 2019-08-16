package com.wangjiangfei.service;

import com.wangjiangfei.domain.ServiceVO;
import com.wangjiangfei.pojo.article.ArticleInfo;
import com.wangjiangfei.pojo.article.ArticleInfoList;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangjiangfei
 * @date 2019/5/30 15:48
 * @description
 */
public interface ArticleService {

    ServiceVO<ArticleInfoList> listArticle(Integer pageNum, Integer pageRow);

    ServiceVO addArticle(ArticleInfo articleInfo, HttpServletRequest request);

    ServiceVO updateArticle(ArticleInfo articleInfo, HttpServletRequest request);

    ServiceVO deleteArticle(Integer articleId, HttpServletRequest request);

    ServiceVO<ArticleInfo> getDetailById(Integer articleId);
}
