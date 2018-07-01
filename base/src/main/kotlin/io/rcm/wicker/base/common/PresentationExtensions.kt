package io.rcm.wicker.base.common

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by joicemarinay on 26/06/2018.
 */
fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
  liveData.observe(this, Observer { it?.let { action(it) } })
}

fun ViewGroup.inflate(layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)