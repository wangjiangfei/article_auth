package com.wangjiangfei.service.impl;

import com.wangjiangfei.config.exception.NoPermissionException;
import com.wangjiangfei.dao.ArticleDao;
import com.wangjiangfei.dao.TokenDao;
import com.wangjiangfei.domain.ServiceVO;
import com.wangjiangfei.domain.SuccessCode;
import com.wangjiangfei.pojo.article.Article;
import com.wangjiangfei.pojo.article.ArticleInfo;
import com.wangjiangfei.pojo.article.ArticleInfoList;
import com.wangjiangfei.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author wangjiangfei
 * @date 2019/5/30 15:48
 * @description
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private TokenDao tokenDao;
    @Autowired
    private ArticleDao articleDao;

    @Override
    public ServiceVO<ArticleInfoList> listArticle(Integer pageNum, Integer pageRow) {
        ArticleInfoList infoList = new ArticleInfoList();
        int count = articleDao.countArticle();
        pageNum = pageNum == 0 ? 1 : pageNum;
        int offSet = (pageNum - 1) * pageRow;
        List<ArticleInfo> infos = articleDao.listArticle(offSet, pageRow);

        infoList.setArticleInfoList(infos);
        infoList.setPageNum(pageNum);
        infoList.setPageRow(pageRow);
        infoList.setCount(count);
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, infoList);
    }

    @Override
    public ServiceVO<ArticleInfo> getDetailById(Integer articleId) {
        ArticleInfo info = articleDao.getDetailById(articleId);
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, info);
    }

    @Override
    public ServiceVO addArticle(ArticleInfo articleInfo, HttpServletRequest request) {
        int userId = tokenDao.getUserIdByTokenName(request.getHeader("token"));
        articleInfo.setCreateUserId(userId);
        articleInfo.setUpdateUserId(userId);
        articleDao.addArticle(articleInfo);
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @Override
    public ServiceVO updateArticle(ArticleInfo articleInfo, HttpServletRequest request) {
        int userId = tokenDao.getUserIdByTokenName(request.getHeader("token"));
        articleInfo.setUpdateUserId(userId);
        articleDao.updateArticle(articleInfo);
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @Override
    public ServiceVO deleteArticle(Integer articleId, HttpServletRequest request) {
        int userId = tokenDao.getUserIdByTokenName(request.getHeader("token"));
        Article articleDB = articleDao.getArticleById(articleId);
        if (articleDB.getCreateUserId() != userId) {
            throw new NoPermissionException();
        }
        articleDao.deleteArticleById(articleId);
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }
}
