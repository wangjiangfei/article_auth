package com.wangjiangfei.dao;

import com.wangjiangfei.pojo.article.Article;
import com.wangjiangfei.pojo.article.ArticleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangjiangfei
 * @date 2019/5/31 8:32
 * @description
 */
public interface ArticleDao {

    Integer countArticle();

    List<ArticleInfo> listArticle(@Param("offSet") Integer offSet, @Param("pageRow") Integer pageRow);

    Integer addArticle(ArticleInfo articleInfo);

    Integer updateArticle(ArticleInfo articleInfo);

    Article getArticleById(Integer articleId);

    Integer deleteArticleById(Integer articleId);

    ArticleInfo getDetailById(Integer articleId);

}
