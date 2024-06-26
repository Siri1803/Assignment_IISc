package com.example.splash_screen_assignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import kotlinx.coroutines.flow.internal.NoOpContinuation.context
import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class MyAdapter(var layouts: IntArray, context: Context) : PagerAdapter(){

    private lateinit var inflater: LayoutInflater

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }



    override fun getCount(): Int {
        return layouts.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(layouts[position], container, attachToRoot:false)
        container.addView(v)
        return v


    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val v = `object` as View
        container.removeView(v)
    }

}