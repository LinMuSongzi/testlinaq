package com.splite.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil.setContentView
import com.splite.test.Comment.start
import com.splite.test.api.RetrofitManager
import com.splite.test.bean.MainUserBean
import com.splite.test.databinding.ActivitySecondBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class SecondActivity : DataBindingActivity<ActivitySecondBinding>() {


    val TAG = "SecondActivity"

    override fun onCreate2(savedInstanceState: Bundle?) {
//        dataBinding.second.text = "安静山东矿机as控件会对客家话"
        dataBinding.second.setOnClickListener {
            ThirdActivity::class.java.start(this)
        }


        var l = 0;


        val one = Observable.create<String> {
            it.onNext("123123akjsdhflkjha")
            it.onComplete()
        }.subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
        val two = Observable.create<Int> {
            it.onNext(l)
            it.onComplete()
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())


        one.compose { top ->
//            two
            Log.i(TAG, "onCreate2: 1 Thread = " + Thread.currentThread())
            Observable.create<Int> { e ->
                Log.i(TAG, "onCreate2: 2 Thread = " + Thread.currentThread())
                top.subscribe {
                    var l = it.length
                    two.subscribe {
                        Log.i(TAG, "onCreate2: 3 Thread = " + Thread.currentThread())
                        e.onNext(l)
                        e.onComplete()
                    }
                }
            }
        }.subscribe {
            Log.i(TAG, "onCreate2: 4 Thread = " + Thread.currentThread())
            Log.i(TAG, "onCreate2: $it")
        }


//        dataBinding.idRecycle.adapter = Cada

    }

}