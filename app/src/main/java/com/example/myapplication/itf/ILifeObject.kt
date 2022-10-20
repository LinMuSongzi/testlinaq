package com.example.myapplication.itf

import androidx.lifecycle.LifecycleOwner

interface ILifeObject :IViewInstance {

    fun getThisLifecycle(): LifecycleOwner?

}