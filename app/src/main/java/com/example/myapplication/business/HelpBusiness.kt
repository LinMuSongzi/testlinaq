package com.example.myapplication.business

import android.R
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.*
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.net.URL


class HelpBusiness(private val activity: FragmentActivity) {


    private val mImageLiveData = MutableLiveData<Bitmap?>()
    val grayImageLiveData = MutableLiveData<Bitmap?>()

    init {
        mImageLiveData.observe(activity) {
            if (it == null) {
                return@observe
            }

// 使用 ImageView 显示处理后的图片
            activity.runOnUiThread {
                grayImageLiveData.value = bitmapGray(it)
            }
        }
    }


    fun bitmapGray(bitmap: Bitmap): Bitmap {
//        val bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image)

// 创建一个Paint对象

// 创建一个Paint对象
        val paint = Paint()

// 创建一个LinearGradient对象，并设置上下左右四个方向的灰度

// 创建一个LinearGradient对象，并设置上下左右四个方向的灰度
        val shader: Shader = LinearGradient(
            0f, 0f, 0f, bitmap.height.toFloat(), intArrayOf(-0x1000000, -0x1), floatArrayOf(0f, 1f),
            Shader.TileMode.CLAMP
        )
        paint.shader = shader

// 将Bitmap对象绘制到Canvas上

// 将Bitmap对象绘制到Canvas上
        val canvas = Canvas()
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        return bitmap;
    }



    companion object {
        const val READ_REQUEST_CODE = 0x98
        const val TAG = "HelpBusiness"

        fun Cursor?.println() {
            this?.apply {

                if (count == 0) {
                    return@apply
                }
//                moveToFirst()
//
//                do {
                val build = java.lang.StringBuilder()
                for (index in 0 until columnCount) {

                    build.append(getColumnName(index) + " , ")

                }
//                } while (moveToNext())

                Log.i(TAG, "println: $build")
            }
        }
    }

    var permission = activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        Log.i(TAG, "permission:  = $it")
        if (it) {
            write()
        }

    }

    var mALLOpenDocumentTree = activity.registerForActivityResult(ActivityResultContracts.OpenDocumentTree()) {
        activity.contentResolver.openInputStream(it!!)?.apply {
            Log.i(TAG, "InputStreamReader(this).readText(): ${InputStreamReader(this).readText()}")
        }
    }

    var mTakePicturePreview: ActivityResultLauncher<Void?> = activity.registerForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) {
        it?.apply {
            Log.i(TAG, "openFileManger: ${it.width}")
            Log.i(TAG, "openFileManger: ${it.height}")
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private var mStartActivityForResult: ActivityResultLauncher<Intent?> =
        activity.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            Log.i(TAG, "startActivityForResult: ${it.resultCode} , ${it.data?.data}")
            MediaStore.createDeleteRequest(activity.contentResolver, mutableListOf<Uri?>().apply {
                add(it.data?.data)
            }).send()
//            Log.i(TAG, "delete: ${activity.contentResolver.delete(it.data?.data!!,null)}")
//            if (it.resultCode == Activity.RESULT_OK) {
//                activity.runOnUiThread {
//                    mImageLiveData.value = BitmapFactory.decodeStream(activity.contentResolver.openInputStream(it.data?.data!!)!!)
//                }
//            }
        }

    private var mStartActivityForText: ActivityResultLauncher<Intent?> =
        activity.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {

//            if (it.resultCode == Activity.RESULT_OK) {
//                Log.i(TAG, "startActivityForResult: ${it.resultCode} , ${activity}}")
//            }
        }


    @RequiresApi(Build.VERSION_CODES.R)
    fun startActivityForResult() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        mStartActivityForResult.launch(intent)
    }

    fun openFileManger() {

        mTakePicturePreview.launch(null)

    }


    fun setimage(context: Activity) {
        val url = "https://img1.baidu.com/it/u=3392591833,1640391553&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=714"
        if (mImageLiveData.value != null) {
            return
        }
        Thread {
            mImageLiveData.postValue(BitmapFactory.decodeStream(URL(url).openConnection().getInputStream()))
        }.start()

//        mImageLiveData.observe()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun operateFile() {
        startActivityForResult()
//        permission.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
//        startActivityForResult()
//        mALLOpenDocumentTree.launch()
//        permission.launch(android.Manifest.permission.MANAGE_DOCUMENTS)
//        write()
    }

    private fun write() {


//        File(Environment.getExternalStorageDirectory(), "deviceId_m.txt").apply {
//            FileOutputStream(this, true).let {
//                it.write(" 哈哈 \n".toByteArray())
//                it.flush()
//                it.close()
//                Log.i(TAG, "operateFile: ok")
//            }
//        }

        File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "deviceId_m2.txt").apply {

            FileOutputStream(this, true).let {
                it.write(" 111哈哈".toByteArray())
                it.flush()
                it.close()
                Log.i(TAG, "operateFile: ok")
            }

            Log.i(TAG, "operateFile: ${readText()}")
        }

    }

    fun setRecycle(recyclerView: RecyclerView) {


    }

}