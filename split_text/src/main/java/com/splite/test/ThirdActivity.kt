package com.splite.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.splite.test.Comment.start
import com.splite.test.R
import com.splite.test.databinding.ActivityNinthBinding
import com.splite.test.databinding.ActivityThirdBinding

class ThirdActivity : DataBindingActivity<ActivityThirdBinding>() {

    override fun onCreate2(savedInstanceState: Bundle?) {
        dataBinding.third.setOnClickListener {
            FourthActivity::class.java.start(this)
        }
    }
}