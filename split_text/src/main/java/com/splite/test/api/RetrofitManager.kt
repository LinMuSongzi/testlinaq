package com.splite.test.api

import com.splite.test.bean.MainUserBean
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import retrofit2.Retrofit

object RetrofitManager {



   fun getApi():SimpleApi{

       return object :SimpleApi{
           override fun getUserInfo(): Observable<MainUserBean> {
               TODO("Not yet implemented")
           }

           override fun getPhone(phone: String): Observable<MainUserBean> {
               TODO("Not yet implemented")
           }

       }

   }

}