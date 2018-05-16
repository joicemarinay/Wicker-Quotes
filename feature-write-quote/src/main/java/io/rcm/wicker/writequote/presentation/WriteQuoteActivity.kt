package io.rcm.wicker.writequote.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.rcm.wicker.writequote.R

/**
 * Created by joicemarinay on 09/05/2018.
 *
 * This Activity is not internal because it is used by
 * [io.rcm.wicker.app.view.ScreenRouterImpl]
 */
class WriteQuoteActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.wicker_write_quote_view)
  }

}