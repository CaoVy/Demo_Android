package com.example.demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.image_view.view.*

class ViewAdapter(private val data: MutableList<Int>): PagerAdapter() {
    override fun getCount() = data.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val v = LayoutInflater.from(container.context).inflate(R.layout.image_view, container, false)
            v.imageTitle.setImageResource(data[position])
            container.addView(v)
            return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}