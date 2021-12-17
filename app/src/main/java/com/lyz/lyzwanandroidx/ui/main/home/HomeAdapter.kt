package com.lyz.lyzwanandroidx.ui.main.home

import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lyz.lyzwanandroidx.R
import com.lyz.lyzwanandroidx.bean.ArticleBean

/**
 * @author liyanze
 * @create 2021/11/22
 * @Describe
 */
class HomeAdapter(data: MutableList<ArticleBean>?) :
    BaseQuickAdapter<ArticleBean, HomeAdapter.HomeViewHolder>(R.layout.item_home, data) {

    override fun convert(helper: HomeViewHolder, item: ArticleBean?) {
        helper.tvChapter?.text = item?.chapterName
        helper.tvTitle?.text = item?.title
        helper.tvAuthor?.text = item?.author
        helper.tvDate?.text = item?.niceDate
    }

    class HomeViewHolder(view: View?) : BaseViewHolder(view) {

        var tvChapter: TextView? = view?.findViewById(R.id.tvChapter)
        var tvTitle: TextView? = view?.findViewById(R.id.tvTitle)
        var tvAuthor: TextView? = view?.findViewById(R.id.tvAuthor)
        var tvDate: TextView? = view?.findViewById(R.id.tvDate)

        init{

        }
    }
}