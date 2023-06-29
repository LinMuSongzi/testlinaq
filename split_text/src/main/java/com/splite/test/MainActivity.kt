package com.splite.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.splite.test.Comment.start
import com.splite.test.bean.MainUserBean
import com.splite.test.databinding.ActivityFifthBinding
import com.splite.test.databinding.ActivityFourthBinding
import com.splite.test.databinding.ActivityFristBinding
import com.splite.test.databinding.ActivityNinthBinding

class MainActivity : DataBindingActivity<ActivityFristBinding>() {

    override fun onCreate2(savedInstanceState: Bundle?) {

//        dataBinding.idMainLayout.bean = MainUserBean("林安琪",2,"广州市天河","13728005599")

        dataBinding.frist.setOnClickListener {
            SecondActivity::class.java.start(this)
        }
    }
}