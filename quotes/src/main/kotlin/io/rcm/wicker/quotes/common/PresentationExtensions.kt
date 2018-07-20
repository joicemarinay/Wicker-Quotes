package io.rcm.wicker.quotes.common

import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View

/**
 * Created by joicemarinay on 17/07/2018.
 */

fun View.showSnackbar(messageResourceId: Int) =
  Snackbar.make(this, messageResourceId, Snackbar.LENGTH_SHORT).show()

fun View.showSnackbarWithAction(@StringRes message: Int, @StringRes actionMessage: Int,
  actionCallback: (View) -> Unit) =
  Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    .setAction(actionMessage, actionCallback).show()