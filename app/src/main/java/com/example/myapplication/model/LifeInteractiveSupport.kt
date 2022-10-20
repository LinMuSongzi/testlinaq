package com.example.myapplication.model

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.ViewModelStoreOwner
import com.example.myapplication.itf.ILifeInteractive

class LifeInteractiveSupport(
    private val lifeInteractive: ILifeInteractive,
    bundle: Bundle?
) :ViewModelStoreOwner by lifeInteractive, LifecycleOwner by lifeInteractive, Factory {

    private var factory = SaveViewModelProviderFactory(lifeInteractive, bundle)
    private val viewModelProvider = ViewModelProvider(this, this)

//    init {
//        lifeInteractive.lifecycle.addObserver(object : DefaultLifecycleObserver {
//            override fun onCreate(owner: LifecycleOwner) {
//
//            }
//
//            override fun onStart(owner: LifecycleOwner) {
//
//                owner.lifecycle.removeObserver(this)
//            }
//        })
//    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return factory.create(modelClass)
    }

    operator fun <T : ViewModel> get(modelClass: Class<T>): T = viewModelProvider[modelClass]


    operator fun <T : ViewModel> get(key: String, modelClass: Class<T>): T = viewModelProvider[key, modelClass]

}