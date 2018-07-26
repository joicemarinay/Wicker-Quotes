package io.rcm.wicker.base.common

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

/**
 * Created by joicemarinay on 26/06/2018.
 */
fun Context.showToast(@StringRes message: Int) {
  Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
  liveData.observe(this, Observer { it?.let { action(it) } })
}

fun View.showSnackbar(messageResourceId: Int) {
  Snackbar.make(this, messageResourceId, Snackbar.LENGTH_SHORT).show()
}

fun View.showSnackbarWithAction(@StringRes message: Int, @StringRes actionMessage: Int,
  actionCallback: (View) -> Unit) {
  Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    .setAction(actionMessage, actionCallback).show()
}

fun View.showSnackbarWithActionAndDismissCallback(@StringRes message: Int,
  @StringRes actionMessage: Int, actionCallback: (View) -> Unit,
  dismissCallback: Snackbar.Callback) {
  Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    .addCallback(dismissCallback)
    .setAction(actionMessage, actionCallback)
    .show()
}

fun ViewGroup.inflate(layoutRes: Int): View {
  return LayoutInflater.from(context).inflate(layoutRes, this, false)
}