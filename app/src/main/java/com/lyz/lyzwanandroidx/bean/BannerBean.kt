package com.lyz.lyzwanandroidx.bean

/**
 * @author liyanze
 * @create 2021/11/03
 * @Describe
 */
data class BannerBean(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)