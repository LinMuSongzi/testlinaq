package com.example.myapplication.model

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

class SaveViewModelProviderFactory(s: SavedStateRegistryOwner,defaultArgs:Bundle?) : AbstractSavedStateViewModelFactory(s,defaultArgs) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        val instance= modelClass.newInstance()
        if(InjectSaveStateHandler::class.java.isAssignableFrom(modelClass)){
            (instance as InjectSaveStateHandler).attachSaveStateHandler(SavedStateHandleWarp(handle))
        }
        return instance
    }


}