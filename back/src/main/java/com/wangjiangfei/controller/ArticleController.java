package com.wangjiangfei.controller;

import com.wangjiangfei.config.annotation.LoginRequired;
import com.wangjiangfei.config.annotation.RequiresPermission;
import com.wangjiangfei.domain.ServiceVO;
import com.wangjiangfei.pojo.article.ArticleInfo;
import com.wangjiangfei.pojo.article.ArticleInfoList;
import com.wangjiangfei.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangjiangfei
 * @date 2019/5/30 15:46
 * @description 文章
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 查询所有文章列表
     */
    @GetMapping("/listArticle")
    @LoginRequired
    @RequiresPermission("article:list")
    public ServiceVO<ArticleInfoList> listArticle(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageRow") Integer pageRow) {
        return articleService.listArticle(pageNum, pageRow);
    }

    /**
     * 查询文章
     */
    @GetMapping("/detail/{articleId}")
    @LoginRequired
    @RequiresPermission("article:list")
    public ServiceVO<ArticleInfo> getDetailById(@PathVariable("articleId") Integer articleId) {
        return articleService.getDetailById(articleId);
    }

    /**
     * 新增文章
     */
    @PostMapping("/addArticle")
    @RequiresPermission("article:add")
    @Transactional(rollbackFor = Exception.class)
    public ServiceVO addArticle(@RequestBody ArticleInfo articleInfo, HttpServletRequest request) {
        return articleService.addArticle(articleInfo, request);
    }

    /**
     * 修改文章
     */
    @PutMapping("/updateArticle")
    @LoginRequired
    @RequiresPermission("article:update")
    @Transactional(rollbackFor = Exception.class)
    public ServiceVO updateArticle(@RequestBody ArticleInfo articleInfo, HttpServletRequest request) {
        return articleService.updateArticle(articleInfo, request);
    }

    /**
     * 删除文章,用户只能删除自己创建的文章
     */
    @DeleteMapping("/deleteArticle/{articleId}")
    @LoginRequired
    @Transactional(rollbackFor = Exception.class)
    public ServiceVO deleteArticle(@PathVariable("articleId") Integer articleId, HttpServletRequest request) {
        return articleService.deleteArticle(articleId, request);
    }
}
