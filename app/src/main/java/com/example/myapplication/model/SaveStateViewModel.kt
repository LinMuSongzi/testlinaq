package com.example.myapplication.model

import androidx.lifecycle.ViewModel
import com.example.myapplication.IHolderSaveStateHandler
import com.example.myapplication.ISaveStateHandle

class SaveStateViewModel: ViewModel() ,InjectSaveStateHandler , IHolderSaveStateHandler {

    lateinit var saveStateHandleWarp : ISaveStateHandle;
    var localSavedHandler :ISaveStateHandle = LocalSavedHandler()
    override fun attachSaveStateHandler(savedHandler: ISaveStateHandle) {
        saveStateHandleWarp = savedHandler;
    }

    override fun getHolderSaveStateHandler(): ISaveStateHandle {
        return saveStateHandleWarp
    }

    fun getLocalHolderSaveStateHandler(): ISaveStateHandle {
        return localSavedHandler
    }

}