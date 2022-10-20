package com.example.myapplication.model

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.myapplication.itf.IHolderSavedStateHandler
import com.example.myapplication.itf.ILifeSaveStateHandler
import com.example.myapplication.itf.ISavedStateHandle

object ViewModelUtil {



    /**
     * 保存基于“key”的value 存储于bundle基于SavedStateHandler api
     */
    @JvmStatic
    fun <T> String.saveStateChange(holder: IHolderSavedStateHandler, v: T) {
        holder.getHolderSavedStateHandle()[this] = v
    }

    /**
     * 保存基于“key”的value 存储于bundle基于SavedStateHandler api
     */
    @JvmStatic
    fun <T> String.saveStateChange(saveStateHandle: ISavedStateHandle, v: T) {
        saveStateHandle[this] = v
    }


    /**
     * 观察数据基于“key”的livedate，
     */
    @JvmStatic
    fun <T> String.liveSaveStateObserver(
        holder: ILifeSaveStateHandler,
        observer: Observer<T>
    ) {
        holder.getThisLifecycle()?.let {
            liveSaveStateObserver(it, holder.getHolderSavedStateHandle(), observer)
        }
    }

    @JvmStatic
    fun <T> String.liveSaveStateObserver(
        lifecycle: LifecycleOwner,
        saveStateHandle: ISavedStateHandle,
        observer: Observer<T>
    ) {
        saveStateHandle.getLiveData<T>(this).observe(lifecycle, observer)
    }


    /**
     * 观察数据基于“key”的livedate，
     * isRemove 是否此次监听仅为一次
     */
    @JvmStatic
    @JvmOverloads
    fun <T> String.liveSaveStateObserverOnOwner(
        holder: ILifeSaveStateHandler,
        observer: Observer<T>,
        l: LifecycleOwner,
        isRemove: Boolean = false,
    ) {
        liveSaveStateObserverOnOwner(
            holder.getHolderSavedStateHandle(),
            holder.getThisLifecycle(),
            observer,
            l,
            isRemove
        )
    }

    @JvmStatic
    @JvmOverloads
    fun <T> String.liveSaveStateObserverOnOwner(
        holder: ISavedStateHandle,
        myLifecycleOwner: LifecycleOwner?,
        observer: Observer<T>,
        otherLifecycleOwner: LifecycleOwner,
        isRemove: Boolean = false,
    ) {
        if (myLifecycleOwner != null) {
            val liveData = holder.getLiveData<T>(this);
            if (isRemove) {
                liveData.observe(otherLifecycleOwner, object : Observer<T> {
                    override fun onChanged(t: T) {
                        observer.onChanged(t)
                        liveData.removeObserver(this)
                    }
                })
            } else {
                liveData.observe(otherLifecycleOwner, observer)
            }
        }
    }


    /**
     * 获取基于“key”的可观察的livedata
     */
    @JvmStatic
    fun <T> String.getSaveStateLiveData(holder: IHolderSavedStateHandler): LiveData<T> {
        return holder.getHolderSavedStateHandle().getLiveData(this);
    }

    @JvmStatic
    fun <T> String.getSaveStateLiveData(saveStateHandle: ISavedStateHandle): LiveData<T> {
        return saveStateHandle.getLiveData(this);
    }

    /**
     * 获取基于“key”的可观察的value
     */
    @JvmStatic
    fun <T> String.getSaveStateValue(holder: IHolderSavedStateHandler): T? {
        return holder.getHolderSavedStateHandle()[this]
    }

    @JvmStatic
    fun <T> String.getSaveStateValue(saveStateHandle: ISavedStateHandle): T? {
        return saveStateHandle[this]
    }

//    @JvmStatic
//    fun <T> String.savedStateAllLiveChangeValue(values: T) {
//        val r: (IHolderSavedStateHandler?, Activity) -> Unit = { f, activity ->
//            if (!activity.isFinishing) {
//                f?.getHolderSavedStateHandle()?.set(this, values)
//            }
//        }
//        for (activity in ActivityLifeManager.getInstance().getLifeActivityList()) {
//            if (activity is FragmentActivity) {
//                for (f in activity.supportFragmentManager.fragments) {
//                    r.invoke(f as? IHolderSavedStateHandler, activity)
//                }
//            }
//        }
//    }

    fun <V:ViewModel> Class<V>.get(l:LifeInteractiveSupport) = l[this]




}