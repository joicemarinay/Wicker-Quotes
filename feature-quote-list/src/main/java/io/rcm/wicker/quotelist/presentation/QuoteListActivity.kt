package io.rcm.wicker.quotelist.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.rcm.wicker.quotelist.R

/**
 * Created by joicemarinay on 09/05/2018.
 */
internal class QuoteListActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.wicker_quote_list_view)
  }
}