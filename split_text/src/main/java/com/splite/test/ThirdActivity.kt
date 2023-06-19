package com.splite.test

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.view.forEach
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.splite.test.Comment.start
import com.splite.test.databinding.ActivityThirdBinding
import com.splite.test.databinding.LayoutViewPageColorBinding
import kotlin.math.abs

class ThirdActivity : DataBindingActivity<ActivityThirdBinding>() {


    val TAG = "ThirdActivity"

    val array = intArrayOf(Color.RED, Color.BLACK, Color.BLUE)



    override fun onCreate2(savedInstanceState: Bundle?) {
        dataBinding.third.setOnClickListener {
            FourthActivity::class.java.start(this)
        }

        dataBinding.idViewpage.adapter = object : PagerAdapter() {
            override fun getCount(): Int {
                return Int.MAX_VALUE - 100
            }

            override fun isViewFromObject(view: View, any: Any): Boolean {
                return view == any
            }

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val dataBinding = LayoutViewPageColorBinding.inflate(layoutInflater, container, true)//layoutInflater.inflate(R.layout.layout_view_page_color, container, false)
                return dataBinding.root.apply {
                    dataBinding.root.tag = position
                    dataBinding.idView.setBackgroundColor(array[position % 3])
                    dataBinding.idText.text = position.toString()
                }
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                container.removeView(`object` as View)
            }

        }


        dataBinding.idViewpage.offscreenPageLimit = 2
        dataBinding.idViewpage.setPageTransformer(true, MyPageTransformer(this))


    }
}