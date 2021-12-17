package com.lyz.lyzwanandroidx.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lyz.lyzwanandroidx.R
import com.lyz.lyzwanandroidx.base.BaseFragment
import com.lyz.lyzwanandroidx.bean.BannerBean
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.*


class HomeFragment : BaseFragment() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    private val homeAdapter by lazy {
        HomeAdapter(null)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initEvent()

        refreshLayout.autoRefresh()

        viewModel.bannerLiveData.observe(viewLifecycleOwner) {
            refreshLayout.finishRefresh(true)
            bannerLayout.getAdapter().setDatas(it)
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            refreshLayout.finishRefresh(false)
            ToastUtils.showShort("${it.message},${it.code}")
        }

        // viewModel.topArticleLiveData.observe(viewLifecycleOwner, {
        //     homeAdapter.addData(0, it)
        // })
        //
        // viewModel.pageArticleLiveData.observe(viewLifecycleOwner, {
        //     homeAdapter.addData(0, it)
        // })

        viewModel.articleLiveData.observe(viewLifecycleOwner,{
            homeAdapter.setNewData(it)
        })
    }

    private fun initView() {

        val banner = (bannerLayout as Banner<BannerBean, BannerImageAdapter<BannerBean>>)
        banner.apply {
            addBannerLifecycleObserver(viewLifecycleOwner)
            setIndicator(CircleIndicator(requireActivity()))
            setAdapter(object : BannerImageAdapter<BannerBean>(viewModel.bannerLiveData.value) {
                override fun onBindView(
                    holder: BannerImageHolder,
                    data: BannerBean,
                    position: Int,
                    size: Int
                ) {
                    Glide.with(this@HomeFragment)
                        .applyDefaultRequestOptions(RequestOptions().fitCenter())
                        .load(data.imagePath)
                        .into(holder.imageView)
                }
            })
        }

        rv.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeAdapter
        }

    }

    private fun initEvent() {
        refreshLayout.setOnRefreshListener(object : OnRefreshListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                viewModel.getBanner()
                viewModel.getArticle()
            }
        })
    }

}