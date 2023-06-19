package com.splite.test

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import androidx.window.embedding.RuleController

class SplitInitializer : Initializer<RuleController> {

    override fun create(context: Context): RuleController {
        return RuleController.getInstance(context).apply {
            Log.i(TAG, "create: SplitInitializer")
            setRules(RuleController.parseRules(context, R.xml.main_split_config))
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }


    companion object{
        const val TAG = "SplitInitializer"
    }
}