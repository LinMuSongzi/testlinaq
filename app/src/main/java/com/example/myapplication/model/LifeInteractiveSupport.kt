package com.example.myapplication.model

import android.os.Bundle
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.ViewModelStoreOwner
import com.example.myapplication.itf.ILifeInteractive

abstract class LifeInteractiveSupport<T : ILifeInteractive>(
    private val lifeInteractive: T,
    bundle: Bundle
) : ViewModelStoreOwner by lifeInteractive, LifecycleOwner by lifeInteractive ,Factory{

    private var factory = SaveViewModelProviderFactory(lifeInteractive, bundle)

    init {
        lifeInteractive.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                businessInit()
            }

            override fun onStart(owner: LifecycleOwner) {
                businessOnStart()
                owner.lifecycle.removeObserver(this)
            }
        })
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return factory.create(modelClass)
    }

    fun businessInit() {

    }

    abstract fun businessOnStart()

}