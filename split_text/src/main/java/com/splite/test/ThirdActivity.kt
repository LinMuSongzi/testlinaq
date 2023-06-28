package com.splite.test

import android.accessibilityservice.AccessibilityService.ScreenshotResult
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Build.VERSION_CODES.S
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.PermissionChecker.PermissionResult
import androidx.core.view.forEach
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.BaseRequestOptions
import com.splite.test.Comment.start
import com.splite.test.databinding.ActivityThirdBinding
import com.splite.test.databinding.LayoutViewPageColorBinding
import java.lang.ref.WeakReference
import java.util.jar.Manifest
import kotlin.math.abs

class ThirdActivity : DataBindingActivity<ActivityThirdBinding>() {


    val TAG = "ThirdActivity"
    val arrayInt = intArrayOf(Color.RED, Color.BLACK, Color.BLUE)
    val array = arrayOf("https://img0.baidu.com/it/u=2726283335,320951208&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500"
        , "https://img1.baidu.com/it/u=2548565113,2783875067&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=500", "https://img1.baidu.com/it/u=2681671445,1640676832&fm=253&fmt=auto&app=138&f=JPEG?w=667&h=500")

    val screenWidth: Int by lazy {
        val w = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val s = w.defaultDisplay
        s.width
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 0x99 && resultCode == Activity.RESULT_OK){
            Log.i(TAG, "onActivityResult: data.data = ${data?.data}")
        }
    }

    override fun onCreate2(savedInstanceState: Bundle?) {
        dataBinding.third.setOnClickListener {
//            FourthActivity::class.java.start(this)

            Dialog(this@ThirdActivity).show()

        }

        val registerPicture = registerForActivityResult(ActivityResultContracts.TakePicturePreview()){
            it?.apply {
                Toast.makeText(this@ThirdActivity, "size = ${it.rowBytes * it.width} byte , width = ${it.width} , height = ${it.height}", Toast.LENGTH_SHORT).show()
            }
        }

        val register = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            if(it){
                registerPicture.launch(null)
            }
        }

        dataBinding.idViewpage.offscreenPageLimit = 1
        val weakReference = WeakReference(dataBinding)
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
//                    dataBinding.root.setBackgroundColor(arrayInt[position % 3])

                    Glide.with(dataBinding.idImage).load(array[position % 3]).into(dataBinding.idImage)
                    dataBinding.idImage.layoutParams.width = (screenWidth * 0.45f).toInt()
                    dataBinding.idImage.setOnClickListener {

                        if(weakReference.get()?.idViewpage?.currentItem == position){
//                            register.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
//                            Toast.makeText(this@ThirdActivity, "show position = $position", Toast.LENGTH_SHORT).show()

                            startActivityForResult(Intent(Intent.ACTION_GET_CONTENT).apply {
                                type = "image/*"
                            },0x99)

                        }else{
                            weakReference.get()?.idViewpage?.currentItem = position
                        }
                    }
//                    dataBinding.idView.layoutParams.he
//                    dataBinding.idText.text = position.toStrin/**/g()
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