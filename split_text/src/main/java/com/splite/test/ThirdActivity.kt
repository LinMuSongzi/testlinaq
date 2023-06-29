package com.splite.test

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.splite.test.databinding.ActivityThirdBinding
import com.splite.test.databinding.LayoutViewPageColorBinding
import com.splite.test.vm.CheckViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.ref.WeakReference

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

    val mCheckViewModel by lazy {
        ViewModelProvider(this@ThirdActivity)[CheckViewModel::class.java]
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 0x99 && resultCode == Activity.RESULT_OK){
            Log.i(TAG, "onActivityResult: data.data = ${data?.data}")
        }
    }


    suspend fun runMore():Int{
        delay(5000)
        mCheckViewModel.savedStateHandle.getLiveData<Int>("one").postValue(1)
        return 1
    }

    override fun onCreate2(savedInstanceState: Bundle?) {
        dataBinding.third.setOnClickListener {
//            FourthActivity::class.java.start(this)

            Dialog(this@ThirdActivity).show()

        }

        mCheckViewModel.viewModelScope.launch (){
            Log.i(TAG, "onCreate2: start ")
            withContext(Dispatchers.IO){
                Log.i(TAG, "onCreate2: ${runMore()}")
            }
            Log.i(TAG, "onCreate2: end ")
            mCheckViewModel.savedStateHandle.getLiveData<Int>("one").observe(this@ThirdActivity){
                Log.i(TAG, "onCreate2: savedStateHandle one = $it")
            }
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