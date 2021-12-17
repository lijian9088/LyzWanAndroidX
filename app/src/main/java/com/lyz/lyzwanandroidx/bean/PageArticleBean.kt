package com.lyz.lyzwanandroidx.bean

/**
 * @author liyanze
 * @create 2021/11/22
 * @Describe
 */
data class PageArticleBean(
    val curPage: Int,
    val datas: List<ArticleBean>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)