package com.lyz.lyzwanandroidx.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lyz.lyzwanandroidx.base.BaseViewModel
import com.lyz.lyzwanandroidx.bean.ArticleBean
import com.lyz.lyzwanandroidx.bean.BannerBean
import com.lyz.lyzwanandroidx.network.ApiException
import com.lyz.lyzwanandroidx.network.WanAndroidNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author liyanze
 * @create 2021/11/22
 * @Describe
 */
class HomeViewModel : BaseViewModel() {

    val bannerLiveData = MutableLiveData<List<BannerBean>>()
    val articleLiveData = MutableLiveData<List<ArticleBean>>()
    val topArticleLiveData = MutableLiveData<List<ArticleBean>>()
    val pageArticleLiveData = MutableLiveData<List<ArticleBean>>()

    fun getBanner() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = WanAndroidNetwork.getBanner()
            if (response.errorCode == 0) {
                bannerLiveData.postValue(response.data)
            } else {
                errorLiveData.postValue(
                    getApiException(
                        ApiException(
                            response.errorMsg,
                            response.errorCode
                        )
                    )
                )
            }
        } catch (e: Exception) {
            errorLiveData.postValue(getApiException(e))
        }
    }

    fun getArticle() {
        viewModelScope.launch {
            val list = mutableListOf<ArticleBean>()
            val topJob = async {
                WanAndroidNetwork.getTopArticle()
            }
            val pageJob = async {
                WanAndroidNetwork.getPageArticle(0)
            }
            val responseTop = topJob.await()
            val responsePage = pageJob.await()
            if (responseTop.errorCode == 0 && responsePage.errorCode == 0) {
                list.addAll(responseTop.data)
                list.addAll(responsePage.data.datas)
                articleLiveData.postValue(list)
            } else {
                errorLiveData.postValue(getApiException(ApiException("文章获取失败", -1000)))
            }
        }
    }

    // suspend fun getTopArticle() = withContext(Dispatchers.IO) {
    //     try {
    //         val response = WanAndroidNetwork.getTopArticle()
    //         if (response.errorCode == 0) {
    //             topArticleLiveData.postValue(response.data)
    //         } else {
    //             errorLiveData.postValue(
    //                 getApiException(
    //                     ApiException(
    //                         response.errorMsg,
    //                         response.errorCode
    //                     )
    //                 )
    //             )
    //         }
    //     } catch (e: Exception) {
    //         errorLiveData.postValue(getApiException(e))
    //     }
    // }
    //
    //
    // fun getPageArticle(page: Int) {
    //     viewModelScope.launch(Dispatchers.IO) {
    //         try {
    //             val response = WanAndroidNetwork.getPageArticle(page)
    //             if (response.errorCode == 0) {
    //                 pageArticleLiveData.postValue(response.data.datas)
    //             } else {
    //                 errorLiveData.postValue(
    //                     getApiException(
    //                         ApiException(
    //                             response.errorMsg,
    //                             response.errorCode
    //                         )
    //                     )
    //                 )
    //             }
    //         } catch (e: Exception) {
    //             errorLiveData.postValue(getApiException(e))
    //         }
    //     }
    // }
}