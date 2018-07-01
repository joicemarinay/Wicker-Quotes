package io.rcm.wicker.quotes.features.list.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.view.View
import android.widget.Toast
import io.rcm.wicker.base.common.observe
import io.rcm.wicker.base.presentation.BaseActivity
import io.rcm.wicker.quotes.QuotesDependencyHolder
import io.rcm.wicker.quotes.R
import io.rcm.wicker.quotes.features.list.injection.QuoteListComponent
import io.rcm.wicker.quotes.features.list.presentation.adapter.QuoteListAdapter
import io.rcm.wicker.quotes.features.list.presentation.adapter.QuoteListViewHolder
import io.rcm.wicker.quotes.presentation.QuoteUi
import io.rcm.wicker.quotes.features.writer.presentation.QuoteWriterActivity
import kotlinx.android.synthetic.main.wicker_quote_list_view.*

/**
 * Created by joicemarinay on 09/05/2018.
 */
internal class QuoteListActivity(override val layoutResourceId: Int = R.layout.wicker_quote_list_view):
    BaseActivity<QuoteListViewModel>(), QuoteListViewHolder.Listener {

  private val component: QuoteListComponent by lazy { QuotesDependencyHolder.listComponent() }
  private val quoteListAdapter: QuoteListAdapter = QuoteListAdapter(this)

  override fun onCreate(savedInstanceState: Bundle?) {
    component.inject(this)
    super.onCreate(savedInstanceState)
    setClickListeners()
    setDataObservers()
    setQuoteListRecyclerView()
  }

  override fun onQuoteClicked(quote: QuoteUi) {
    Toast.makeText(this, "${quote.quote}", Toast.LENGTH_SHORT).show()
  }

  private fun setClickListeners() {
    quoteList_fab_addQuote.setOnClickListener { openQuoteWriter() }
  }

  private fun setDataObservers() {
    observe(viewModel.state()) { onStateChange(it) }
  }

  private fun setQuoteListRecyclerView() {
    quoteList_recyclerView_quotes.adapter = quoteListAdapter
    quoteList_recyclerView_quotes.addItemDecoration(
        DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
  }

  private fun showEmptyView() {
    quoteList_empty.visibility = View.VISIBLE
    quoteList_recyclerView_quotes.visibility = View.GONE
  }

  private fun showError() {
    //TODO
  }

  private fun showLoading() {
    //TODO
  }

  private fun showQuoteList() {
    quoteList_empty.visibility = View.GONE
    quoteList_recyclerView_quotes.visibility = View.VISIBLE
  }

  private fun onStateChange(state: QuoteListViewModel.State) = when(state) {
    is QuoteListViewModel.State.QuotesLoaded -> quoteListAdapter.setQuoteList(state.quotes)
    QuoteListViewModel.State.GetQuotesOk -> showQuoteList()
    QuoteListViewModel.State.GetQuotesOkEmpty -> showEmptyView()
    QuoteListViewModel.State.GetQuotesFailed -> showError()
    QuoteListViewModel.State.ShowLoading -> showLoading()
  }

  //TODO add [QuoteEntity] as param (when editing a quote)
  private fun openQuoteWriter() {
    startActivity(Intent(this, QuoteWriterActivity::class.java))
  }
}