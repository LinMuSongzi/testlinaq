package com.splite.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.splite.test.Comment.start
import com.splite.test.databinding.ActivityFourthBinding
import com.splite.test.databinding.ActivityNinthBinding

class FourthActivity : DataBindingActivity<ActivityFourthBinding>() {

    override fun onCreate2(savedInstanceState: Bundle?) {
        dataBinding.fourth.setOnClickListener {
            FifthActivity::class.java.start(this)
        }
    }
}