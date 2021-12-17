package com.lyz.lyzwanandroidx.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lyz.lyzwanandroidx.bean.BaseBean
import com.lyz.lyzwanandroidx.network.ApiException
import com.lyz.lyzwanandroidx.network.WanAndroidNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException

/**
 * @author liyanze
 * @create 2021/11/22
 * @Describe
 */
open class BaseViewModel : ViewModel() {

    val errorLiveData = MutableLiveData<ApiException>()

    fun getApiException(e: Throwable): ApiException {
        return when (e) {
            is UnknownHostException -> ApiException("连接异常", 1001)
            is JSONException -> ApiException("解析异常", 1002)
            is SocketTimeoutException -> ApiException("连接超时", 1003)
            is ConnectException -> ApiException("连接错误", 1004)
            is HttpException -> ApiException("http code ${e.code()}", 1005)
            is ApiException -> e
            /**
             * 如果协程还在运行，个别机型退出当前界面时，viewModel会通过抛出CancellationException，
             * 强行结束协程，与java中InterruptException类似，所以不必理会,只需将toast隐藏即可
             */
            is CancellationException -> {
                ApiException("", -10)
            }
            else -> {
                ApiException("未知错误", -100)
            }
        }
    }

}