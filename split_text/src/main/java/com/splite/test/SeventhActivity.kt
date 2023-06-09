package com.splite.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.splite.test.Comment.start
import com.splite.test.databinding.ActivityNinthBinding
import com.splite.test.databinding.ActivitySeventhBinding

class SeventhActivity : DataBindingActivity<ActivitySeventhBinding>() {

    override fun onCreate2(savedInstanceState: Bundle?) {
        dataBinding.seventh.setOnClickListener {
            EighthActivity::class.java.start(this)
        }
    }
}