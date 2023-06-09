package com.splite.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import com.splite.test.Comment.start
import com.splite.test.databinding.ActivitySecondBinding

class SecondActivity : DataBindingActivity<ActivitySecondBinding>() {
    override fun onCreate2(savedInstanceState: Bundle?) {
//        dataBinding.second.text = "安静山东矿机as控件会对客家话"
        dataBinding.second.setOnClickListener {
            ThirdActivity::class.java.start(this)
        }
    }

}