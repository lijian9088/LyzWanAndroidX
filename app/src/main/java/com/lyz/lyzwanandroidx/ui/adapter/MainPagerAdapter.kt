package com.lyz.lyzwanandroidx.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @author liyanze
 * @create 2021/05/13
 * @Describe
 */
class MainPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior) {

    private var fragmentList = mutableListOf<Fragment>()

    fun setList(list: MutableList<Fragment>) {
        fragmentList = list
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }
}