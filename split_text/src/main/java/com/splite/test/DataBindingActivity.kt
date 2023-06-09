package com.splite.test

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

class DataBindingActivity<D : ViewDataBinding> : AppCompatActivity() {


    val dataBinding by lazy {


        (this@DataBindingActivity.javaClass.genericSuperclass as Class<D>).apply {



//            this.getConstructor()


        }
    }




}