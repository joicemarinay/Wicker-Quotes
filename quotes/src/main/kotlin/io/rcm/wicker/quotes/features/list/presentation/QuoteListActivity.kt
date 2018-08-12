package io.rcm.wicker.quotes.features.list.presentation

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.view.Menu
import android.view.MenuItem
import android.view.View
import io.rcm.wicker.base.common.observe
import io.rcm.wicker.base.common.showSnackbarWithActionAndDismissCallback
import io.rcm.wicker.base.presentation.BaseActivity
import io.rcm.wicker.quotes.QuotesDependencyHolder
import io.rcm.wicker.quotes.R
import io.rcm.wicker.quotes.common.EXTRA_DELETED_QUOTE
import io.rcm.wicker.quotes.features.details.presentation.QuoteDetailsActivity
import io.rcm.wicker.quotes.features.list.injection.QuoteListComponent
import io.rcm.wicker.quotes.features.list.presentation.QuoteListState.GetQuotesFailed
import io.rcm.wicker.quotes.features.list.presentation.QuoteListState.Loading
import io.rcm.wicker.quotes.features.list.presentation.QuoteListState.QuotesEmpty
import io.rcm.wicker.quotes.features.list.presentation.QuoteListState.QuotesLoaded
import io.rcm.wicker.quotes.features.list.presentation.adapter.QuoteListAdapter
import io.rcm.wicker.quotes.features.list.presentation.adapter.QuoteListViewHolder
import io.rcm.wicker.quotes.features.writer.presentation.QuoteWriterActivity
import io.rcm.wicker.quotes.presentation.QuoteUi
import kotlinx.android.synthetic.main.wicker_quote_list_view.*

/**
 * Created by joicemarinay on 09/05/2018.
 */
internal class QuoteListActivity(override val layoutResourceId: Int = R.layout.wicker_quote_list_view):
    BaseActivity<QuoteListViewModel, QuoteListState>(), QuoteListViewHolder.Listener {

  private val component: QuoteListComponent by lazy { QuotesDependencyHolder.listComponent() }
  private val quoteListAdapter: QuoteListAdapter = QuoteListAdapter(this)

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (REQUEST_CODE_DETAILS == requestCode) {
      onQuoteDetailsResult(resultCode, data)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    component.inject(this)
    super.onCreate(savedInstanceState)
    setClickListeners()
    setDataObservers()
    setQuoteListRecyclerView()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.wicker_menu_quote_list, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      android.R.id.home -> {
        onBackPressed()
        return true
      }
      R.id.wicker_menu_quotelist_settings -> {
        openSettings()
        return true
      }
      else -> return super.onOptionsItemSelected(item)
    }
  }

  override fun onQuoteClicked(quote: QuoteUi) {
    startActivityForResult(QuoteDetailsActivity.intent(this, quote), REQUEST_CODE_DETAILS)
  }

  override fun onStateChange(state: QuoteListState) = when(state) {
    is GetQuotesFailed -> showError()
    is Loading -> showLoading()
    is QuotesEmpty -> showEmptyView()
    is QuotesLoaded -> showQuoteList(state.quotes)
  }

  override fun setToolbar() {
    super.setToolbar()
    supportActionBar?.subtitle = getString(R.string.label_your_collection)
  }

  private fun onQuoteDetailsResult(resultCode: Int, data: Intent?) {
    when (resultCode) {
      QuoteDetailsActivity.DELETE_RESULT_OK ->
        data?.getParcelableExtra<QuoteUi>(EXTRA_DELETED_QUOTE)?.let { onQuoteDeleted(it) }
    }
  }

  private fun onQuoteDeleted(deletedQuote: QuoteUi) {
    quoteList_parent.showSnackbarWithActionAndDismissCallback(message = R.string.spiel_quote_deleted,
      actionMessage = R.string.action_undo, actionCallback =  { viewModel.undoDelete(deletedQuote) },
      dismissCallback = object:Snackbar.Callback() {
        override fun onDismissed(snackbar: Snackbar, event: Int) {
          //Hard-delete quote if Undo action is ignored (not tapped)
          if (event != DISMISS_EVENT_ACTION) {
            viewModel.ignoreUndoDelete(deletedQuote)
          }
        }
      })
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

  private fun showQuoteList(quotes: List<QuoteUi>) {
    quoteListAdapter.setQuoteList(quotes)
    quoteList_empty.visibility = View.GONE
    quoteList_recyclerView_quotes.visibility = View.VISIBLE
  }

  private fun openQuoteWriter() {
    startActivity(Intent(this, QuoteWriterActivity::class.java))
  }

  private fun openSettings() {
    startActivity(Intent(this, Class.forName("io.rcm.wicker.settings.presentation.SettingsActivity")))
  }

  companion object {
    private const val REQUEST_CODE_DETAILS = 1
  }
}