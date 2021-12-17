package com.lyz.lyzwanandroidx.network

import com.lyz.lyzwanandroidx.bean.ArticleBean
import com.lyz.lyzwanandroidx.bean.BannerBean
import com.lyz.lyzwanandroidx.bean.BaseBean
import com.lyz.lyzwanandroidx.bean.PageArticleBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author liyanze
 * @create 2021/11/02
 * @Describe
 */
interface WanAndroidService {

    @GET("banner/json")
    fun getBanner(): Call<BaseBean<List<BannerBean>>>

    @GET("article/top/json")
    fun getTopArticle(): Call<BaseBean<List<ArticleBean>>>

    @GET("article/list/{page}/json")
    fun getPageArticle(@Path("page") page: Int): Call<BaseBean<PageArticleBean>>

}