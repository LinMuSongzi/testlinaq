package com.splite.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.splite.test.Comment.start
import com.splite.test.databinding.ActivityEighthBinding
import com.splite.test.databinding.ActivityNinthBinding

class EighthActivity : DataBindingActivity<ActivityEighthBinding>() {

    /**
     * 所有技能一局仅使用一次
     * 进攻：
     *      连续摸两次
     *      可以吃上家的牌
     * 运营：
     *      可以查看左右两家的牌，对方可以不给牌数查看最多3张
     *      当对方准备抓牌时候，你可以用不是番的牌来替换对方要摸的牌
     * 防守：
     *      当对方要碰/杠的时候，你可以指定此牌不允许碰/杠。番字除外
     *      当任何人出杠的时候，你可以用自己不是番字的一张牌替换掉尾数的牌。
     * 综合：
     *      开始后13牌抓完，随机抽取3张盖住，剩下10张作为牌面即可。当自摸的时候这三张牌将作为奖码
     *      你可以替换这三张牌，但是他们无法在作为奖码。虽然你的牌面即只有10张
     *
     */

    override fun onCreate2(savedInstanceState: Bundle?) {
        dataBinding.eighth.setOnClickListener {
            NinthActivity::class.java.start(this)
        }
    }
}