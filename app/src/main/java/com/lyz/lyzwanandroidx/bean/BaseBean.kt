package com.lyz.lyzwanandroidx.bean

/**
 * @author liyanze
 * @create 2021/11/03
 * @Describe
 */
class BaseBean<T : Any>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
)