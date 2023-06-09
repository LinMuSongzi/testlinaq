package com.splite.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.splite.test.Comment.start
import com.splite.test.databinding.ActivityNinthBinding

class NinthActivity : DataBindingActivity<ActivityNinthBinding>() {

    override fun onCreate2(savedInstanceState: Bundle?) {
        dataBinding.ninth.setOnClickListener {
            SecondActivity::class.java.start(this)
        }
    }
}