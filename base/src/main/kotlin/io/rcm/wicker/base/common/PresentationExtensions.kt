package io.rcm.wicker.base.common

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.support.annotation.ColorInt
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast

/**
 * Created by joicemarinay on 26/06/2018.
 */
fun Context.getColorRes(@ColorInt color: Int) = ContextCompat.getColor(this, color)

fun Context.showToast(@StringRes message: Int) {
  Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
  liveData.observe(this, Observer { it?.let { action(it) } })
}

fun EditText.setTextChangeListener(afterTextChange: (String) -> Unit) {
  this.addTextChangedListener(object: TextWatcher {
    override fun afterTextChanged(s: Editable?) {
      afterTextChange(s.toString())
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

  })
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