package com.splite.test

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.splite.test.databinding.ActivitySecondBinding
import java.lang.reflect.ParameterizedType

abstract class DataBindingActivity<D : ViewDataBinding> : AppCompatActivity() {


    protected val dataBinding: D by lazy {
        (this@DataBindingActivity.javaClass.genericSuperclass as ParameterizedType).let {
//            ActivitySecondBinding.inflate()
            (it.actualTypeArguments[0] as Class<*>).getDeclaredMethod("inflate",LayoutInflater::class.java).invoke(null,layoutInflater) as D
        }
    }

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(dataBinding.root)
        onCreate2(savedInstanceState)
    }

    abstract fun onCreate2(savedInstanceState: Bundle?)


}