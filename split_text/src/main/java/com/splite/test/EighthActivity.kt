package com.splite.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.splite.test.Comment.start
import com.splite.test.databinding.ActivityEighthBinding
import com.splite.test.databinding.ActivityNinthBinding

class EighthActivity : DataBindingActivity<ActivityEighthBinding>() {

    override fun onCreate2(savedInstanceState: Bundle?) {
        dataBinding.eighth.setOnClickListener {
            NinthActivity::class.java.start(this)
        }
    }
}