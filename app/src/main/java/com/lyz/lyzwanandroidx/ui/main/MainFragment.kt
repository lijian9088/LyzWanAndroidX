package com.lyz.lyzwanandroidx.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lyz.lyzwanandroidx.R
import com.lyz.lyzwanandroidx.base.BaseFragment
import com.lyz.lyzwanandroidx.ui.adapter.MainPagerAdapter
import com.lyz.lyzwanandroidx.ui.main.home.HomeFragment
import com.lyz.lyzwanandroidx.ui.main.me.MeFragment
import com.lyz.lyzwanandroidx.ui.main.project.ProjectFragment
import com.lyz.lyzwanandroidx.ui.main.square.SquareFragment
import com.lyz.lyzwanandroidx.ui.main.wechat.WechatFragment


class MainFragment : BaseFragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var bottomNavigationView: BottomNavigationView
    private val titleList = mutableListOf("首页", "项目", "广场", "公众号", "我的")
    private var fragmentList: MutableList<Fragment> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initFragment()
        initBottomNav()
        initViewPager()
    }

    private fun initView(view: View) {
        viewPager = view.findViewById(R.id.viewPager)
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView)
        // magicIndicator = view.findViewById(R.id.magicIndicator)

        // val commonNavigator = CommonNavigator(requireContext())
        // commonNavigator.adapter = object : CommonNavigatorAdapter() {
        //     override fun getCount(): Int {
        //         return titleList.size
        //     }
        //
        //     override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
        //         val titleView = ColorTransitionPagerTitleView(requireContext())
        //         titleView.normalColor = Color.GRAY
        //         titleView.selectedColor = Color.BLACK
        //         titleView.text = titleList[index]
        //         titleView.setOnClickListener {
        //
        //         }
        //         return titleView
        //     }
        //
        //     override fun getIndicator(context: Context?): IPagerIndicator {
        //         val indicator = LinePagerIndicator(requireContext())
        //         indicator.mode = LinePagerIndicator.MODE_MATCH_EDGE
        //         return indicator
        //     }
        // }
        //
        // magicIndicator.navigator = commonNavigator
    }

    private fun initFragment() {
        if (fragmentList.size == 0) {
            fragmentList.add(HomeFragment())
            fragmentList.add(ProjectFragment())
            fragmentList.add(SquareFragment())
            fragmentList.add(WechatFragment())
            fragmentList.add(MeFragment())
        }
    }

    private fun initBottomNav() {
        bottomNavigationView.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.main_home -> viewPager.currentItem = 0
                    R.id.main_project -> viewPager.currentItem = 1
                    R.id.main_square -> viewPager.currentItem = 2
                    R.id.main_wechat -> viewPager.currentItem = 3
                    R.id.main_me -> viewPager.currentItem = 4
                }
                return true
            }
        })
        bottomNavigationView.selectedItemId = R.id.main_home
    }

    private fun initViewPager() {
        viewPager.adapter = MainPagerAdapter(
            parentFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        ).apply {
            setList(fragmentList)
        }
        viewPager.offscreenPageLimit = 4
    }
}