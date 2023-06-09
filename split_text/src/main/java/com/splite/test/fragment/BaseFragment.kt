package com.splite.test.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: ${javaClass.simpleName}")
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        Log.i(TAG, "onCreateAnimation: ${javaClass.simpleName}")
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        Log.i(TAG, "onCreateContextMenu: ${javaClass.simpleName}")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated: ${javaClass.simpleName}")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i(TAG, "onAttach: ${javaClass.simpleName}")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(TAG, "onCreateView: ${javaClass.simpleName}")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ${javaClass.simpleName}")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: ${javaClass.simpleName}")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.i(TAG, "onViewStateRestored: ${javaClass.simpleName}")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: ${javaClass.simpleName}")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: ${javaClass.simpleName}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ${javaClass.simpleName}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG, "onDestroyView: ${javaClass.simpleName}")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i(TAG, "onDetach: ${javaClass.simpleName}")
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        Log.i(TAG, "onDestroyOptionsMenu: ${javaClass.simpleName}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState: ${javaClass.simpleName}")
    }

    companion object
    {
        const val TAG = "BaseFragment"
    }

}