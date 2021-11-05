package com.lyz.lyzwanandroidx.bean

/**
 * @author liyanze
 * @create 2021/11/03
 * @Describe
 */
data class BaseBean<T>(
    val `data`: List<T>,
    val errorCode: Int,
    val errorMsg: String
)