package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.example.myapplication.business.HelpBusiness
import com.example.myapplication.business.RecycleViewBusiness.Companion.build
import com.example.myapplication.databinding.ActivityScrollingBinding
import com.example.myapplication.itf.ILifeInteractive
import com.example.myapplication.model.LifeInteractiveSupport
import com.example.myapplication.model.SimpleViewModel
import com.example.myapplication.model.ViewModelUtil.get
import com.example.myapplication.model.ViewModelUtil.liveSaveStateObserver
import com.example.myapplication.model.ViewModelUtil.saveStateChange

class ScrollingActivity : AppCompatActivity(), ILifeInteractive {

    private lateinit var binding: ActivityScrollingBinding

    lateinit var lifeInteractive: LifeInteractiveSupport
    private val mHelpBusiness = HelpBusiness(this)

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifeInteractive = LifeInteractiveSupport(this, savedInstanceState)

        mHelpBusiness.grayImageLiveData.observe(this){
            it?.apply {
//                binding.idCenter.idImage?.setImageBitmap(it)
            }
        }

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title

        binding.fab.setOnClickListener { view ->
            mHelpBusiness.setimage(this@ScrollingActivity)
//            mHelpBusiness.startActivityForResult()
//            mHelpBusiness.openFileManger()
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
        }


        val vm = SimpleViewModel::class.java.get(lifeInteractive)
        "text".liveSaveStateObserver<String>(this, vm.getHolderSavedStateHandle()) {
            println(" show $it")
            binding.toolbarLayout.title = it
        }
        var sum = 0
//        val r = object : Runnable {
//            override fun run() {
        "text".saveStateChange(vm, "hello" + sum++)
////                if (lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
//                    Handler().postDelayed(this, 1000)
////                }
//            }
//        }
//        r.run()

        startService(Intent(this, MusicService::class.java))


        binding.idRecycleview.build()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}