package com.example.myapplication.model

import com.example.myapplication.ISaveStateHandle

internal interface InjectSaveStateHandler {

    fun attachSaveStateHandler(savedHandler: ISaveStateHandle);

}