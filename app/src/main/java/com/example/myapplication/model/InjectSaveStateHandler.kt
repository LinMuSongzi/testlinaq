package com.example.myapplication.model

import com.example.myapplication.itf.ISavedStateHandle

internal interface InjectSaveStateHandler {

    fun attachSaveStateHandler(savedHandler: ISavedStateHandle);

}