package com.splite.test

import android.os.Bundle
import com.splite.test.Comment.start
import com.splite.test.databinding.ActivityFifthBinding

class FifthActivity : DataBindingActivity<ActivityFifthBinding>() {

    override fun onCreate2(savedInstanceState: Bundle?) {
        dataBinding.fifth.setOnClickListener {
            SixthActivity::class.java.start(this)
        }
    }
}