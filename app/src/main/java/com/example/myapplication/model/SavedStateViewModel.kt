package com.example.myapplication.model

import androidx.lifecycle.ViewModel
import com.example.myapplication.itf.IHolderSavedStateHandler
import com.example.myapplication.itf.ISaveStateHandle

class SavedStateViewModel: ViewModel() ,InjectSaveStateHandler , IHolderSavedStateHandler {

    lateinit var saveStateHandleWarp : ISaveStateHandle;
    var localSavedHandler : ISaveStateHandle = LocalSavedHandler()
    override fun attachSaveStateHandler(savedHandler: ISaveStateHandle) {
        saveStateHandleWarp = savedHandler;
    }

    override fun getHolderSavedStateHandle(): ISaveStateHandle {
        return saveStateHandleWarp
    }

    fun getLocalHolderSaveStateHandler(): ISaveStateHandle {
        return localSavedHandler
    }


}