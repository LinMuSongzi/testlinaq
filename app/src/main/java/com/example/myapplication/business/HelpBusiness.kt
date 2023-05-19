package com.example.myapplication.business

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.util.jar.Manifest


class HelpBusiness(private val activity: FragmentActivity) {


    val mImageLiveData = MutableLiveData<Bitmap?>()

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


    fun startActivityForResult() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        mStartActivityForResult.launch(intent)
    }

    fun openFileManger() {

        mTakePicturePreview.launch(null)

    }

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