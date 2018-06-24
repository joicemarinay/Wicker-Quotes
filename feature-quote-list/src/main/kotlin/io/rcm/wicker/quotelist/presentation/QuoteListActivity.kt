package io.rcm.wicker.quotelist.presentation

import android.os.Bundle
import io.rcm.wicker.base.presentation.BaseActivity
import io.rcm.wicker.base.presentation.ScreenRouter
import io.rcm.wicker.base.presentation.ScreenRouter.Screen
import io.rcm.wicker.quotelist.R
import kotlinx.android.synthetic.main.wicker_quote_list_view.*
import javax.inject.Inject

/**
 * Created by joicemarinay on 09/05/2018.
 */
class QuoteListActivity(override val layoutResourceId: Int = R.layout.wicker_quote_list_view):
    BaseActivity() {

  @Inject
  lateinit var screenRouter: ScreenRouter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setFab()
  }

  private fun setFab() {
    quoteList_fab_addQuote.setOnClickListener { openWriteQuote() }
  }

  //TODO add [QuoteEntity] as param
  private fun openWriteQuote() {
    startActivity(screenRouter.getScreenIntent(this, Screen.Write))
  }
}