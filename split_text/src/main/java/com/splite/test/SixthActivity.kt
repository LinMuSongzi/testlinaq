package com.splite.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.splite.test.Comment.start
import com.splite.test.databinding.ActivityNinthBinding
import com.splite.test.databinding.ActivitySixthBinding

class SixthActivity : DataBindingActivity<ActivitySixthBinding>() {

    override fun onCreate2(savedInstanceState: Bundle?) {
        dataBinding.sixth.setOnClickListener {
            SeventhActivity::class.java.start(this)
        }
    }
}