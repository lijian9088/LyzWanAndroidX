package com.lyz.lyzwanandroidx.ui.main.wechat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lyz.lyzwanandroidx.R
import com.lyz.lyzwanandroidx.base.BaseFragment

class WechatFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wechat, container, false)
    }

}