package com.example.myapplication.itf

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.ViewTreeViewModelStoreOwner
import androidx.savedstate.SavedStateRegistryOwner

interface ILifeInteractive : SavedStateRegistryOwner, LifecycleOwner, ViewModelStoreOwner {
}