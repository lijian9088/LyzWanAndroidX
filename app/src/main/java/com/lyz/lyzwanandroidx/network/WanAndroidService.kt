package com.lyz.lyzwanandroidx.network

import com.lyz.lyzwanandroidx.bean.BannerBean
import com.lyz.lyzwanandroidx.bean.BaseBean
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author liyanze
 * @create 2021/11/02
 * @Describe
 */
interface WanAndroidService {

    @GET("banner/json")
    fun getBanner(): Call<BaseBean<BannerBean>>

}