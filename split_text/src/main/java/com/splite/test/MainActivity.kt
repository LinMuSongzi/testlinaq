package com.splite.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.splite.test.Comment.start
import com.splite.test.databinding.ActivityFifthBinding
import com.splite.test.databinding.ActivityFourthBinding
import com.splite.test.databinding.ActivityFristBinding
import com.splite.test.databinding.ActivityNinthBinding

class MainActivity : DataBindingActivity<ActivityFristBinding>() {

    override fun onCreate2(savedInstanceState: Bundle?) {
        dataBinding.frist.setOnClickListener {
            SecondActivity::class.java.start(this)
        }
    }
}