package com.splite.test

import android.content.Context
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.viewpager.widget.ViewPager

class MyPageTransformer(var context: Context) : ViewPager.PageTransformer {
    /**
     * 功能：获得屏幕宽度（px）
     */
    val screenWidth: Int by lazy {
        val w = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val s = w.defaultDisplay
        s.width
    }

    val SMALLER_SCALE = 0.33f
    val MY_CLIP_WIDTH = screenWidth / 2 * 1.43f//挤过来的距离
//            val _1_2 = (MY_CLIP_WIDTH * 0.2f).toInt()

    override fun transformPage(p: View, position: Float) {

        val page = p//.findViewById<View>(R.id.id_view)

        Log.i("transformPage", ": position = $position , page = ${page.hashCode()} ")
//            if(position > 1)
        page.translationX = -(MY_CLIP_WIDTH) * position

        if (position <= 1 && position > 0) {
            val s = SMALLER_SCALE + (1 - SMALLER_SCALE) * (1 - position)
            page.scaleX = s
            page.scaleY = s
        } else if (position >= -1 && position < 0) {
            val s = SMALLER_SCALE + (1 - SMALLER_SCALE) * (1 + position)
            page.scaleX = s
            page.scaleY = s
        } else if (position > 1 || position < -1) {
            page.scaleX = SMALLER_SCALE
            page.scaleY = SMALLER_SCALE
        } else {
            page.scaleX = 1f
            page.scaleY = 1f
        }
        val positionInt = position.toInt()
        if (positionInt == 0) {
            page.z = 1f
        } else if (positionInt == -1 || positionInt == 1 || positionInt == -2 || positionInt == 2) {
            page.z = 0f
        }
    }
}