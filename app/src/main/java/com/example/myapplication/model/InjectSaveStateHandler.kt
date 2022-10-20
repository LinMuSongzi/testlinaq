package com.example.myapplication.model

import com.example.myapplication.itf.ISaveStateHandle

internal interface InjectSaveStateHandler {

    fun attachSaveStateHandler(savedHandler: ISaveStateHandle);

}