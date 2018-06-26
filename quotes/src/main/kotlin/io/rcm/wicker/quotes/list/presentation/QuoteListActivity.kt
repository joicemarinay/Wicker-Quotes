package io.rcm.wicker.quotes.list.presentation

import android.content.Intent
import android.os.Bundle
import io.rcm.wicker.base.presentation.BaseActivity
import io.rcm.wicker.quotes.QuotesDependencyHolder
import io.rcm.wicker.quotes.R
import io.rcm.wicker.quotes.list.injection.QuoteListComponent
import io.rcm.wicker.quotes.writer.presentation.QuoteWriterActivity
import kotlinx.android.synthetic.main.wicker_quote_list_view.*

/**
 * Created by joicemarinay on 09/05/2018.
 */
internal class QuoteListActivity(override val layoutResourceId: Int = R.layout.wicker_quote_list_view):
    BaseActivity<QuoteListViewModel>() {

  private val component: QuoteListComponent by lazy { QuotesDependencyHolder.listComponent() }

  override fun onCreate(savedInstanceState: Bundle?) {
    component.inject(this)
    super.onCreate(savedInstanceState)
    setClickListeners()
  }

  private fun setClickListeners() {
    quoteList_fab_addQuote.setOnClickListener { openQuoteWriter() }
  }

  //TODO add [QuoteEntity] as param (when editing a quote)
  private fun openQuoteWriter() {
    startActivity(Intent(this, QuoteWriterActivity::class.java))
  }
}