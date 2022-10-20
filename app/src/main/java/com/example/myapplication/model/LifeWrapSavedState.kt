package com.example.myapplication.model

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LifecycleOwner
import com.example.myapplication.itf.IHolderSavedStateHandler
import com.example.myapplication.itf.ILifeSaveStateHandler
import com.example.myapplication.itf.ISaveStateHandle

class LifeWrapSavedState(var lifecycleOwner: LifecycleOwner, var holder:IHolderSavedStateHandler) :ILifeSaveStateHandler {
    override fun getThisLifecycle(): LifecycleOwner {
        return lifecycleOwner
    }

    override fun runOnUiThread(runnable: Runnable) {
        Handler(Looper.getMainLooper()).post(runnable)
    }

    override fun getHolderSavedStateHandle(): ISaveStateHandle {
        return holder.getHolderSavedStateHandle()
    }
}