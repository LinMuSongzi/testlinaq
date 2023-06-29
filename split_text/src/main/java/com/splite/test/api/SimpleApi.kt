package com.splite.test.api

import com.splite.test.bean.MainUserBean
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface SimpleApi {



    @GET("asdasd")
    fun getUserInfo(): Observable<MainUserBean>

    @GET("asdasd")
    fun getPhone(phone:String):Observable<MainUserBean>

}