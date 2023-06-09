package com.splite.test

import android.app.Activity
import android.content.Context
import android.content.Intent

object Comment {

    fun <T : Activity> Class<T>.start(context: Context?) {
        context?.apply {
            startActivity(Intent(this,this@start))
        }
    }

}