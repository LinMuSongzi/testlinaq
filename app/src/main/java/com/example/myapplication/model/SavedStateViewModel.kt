package com.example.myapplication.model

import androidx.lifecycle.ViewModel
import com.example.myapplication.itf.IHolderSavedStateHandler
import com.example.myapplication.itf.ISavedStateHandle

open class SavedStateViewModel: ViewModel() ,InjectSaveStateHandler , IHolderSavedStateHandler {

    private lateinit var saveStateHandleWarp : ISavedStateHandle;
    private var localSavedHandler : ISavedStateHandle = LocalSavedHandler()
    override fun attachSaveStateHandler(savedHandler: ISavedStateHandle) {
        saveStateHandleWarp = savedHandler;
    }

    override fun getHolderSavedStateHandle(): ISavedStateHandle {
        return saveStateHandleWarp
    }

    fun getLocalHolderSaveStateHandler(): ISavedStateHandle {
        return localSavedHandler
    }


}