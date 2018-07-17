package io.rcm.wicker.quotes.common

import android.support.design.widget.Snackbar
import android.view.View

/**
 * Created by joicemarinay on 17/07/2018.
 */

fun View.showSnackbar(messageResourceId: Int) =
  Snackbar.make(this, messageResourceId, Snackbar.LENGTH_SHORT).show()