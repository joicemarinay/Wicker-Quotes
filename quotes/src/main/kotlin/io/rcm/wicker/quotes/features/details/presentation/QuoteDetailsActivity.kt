package io.rcm.wicker.quotes.features.details.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.view.Menu
import android.view.MenuItem
import io.rcm.wicker.base.common.KEY_PREFIX
import io.rcm.wicker.base.common.observe
import io.rcm.wicker.base.presentation.BaseActivity
import io.rcm.wicker.quotes.QuotesDependencyHolder
import io.rcm.wicker.quotes.R
import io.rcm.wicker.quotes.features.details.injection.QuoteDetailsComponent
import io.rcm.wicker.quotes.features.details.presentation.QuoteDetailsState.*
import io.rcm.wicker.quotes.features.writer.presentation.QuoteWriterActivity
import io.rcm.wicker.quotes.presentation.QuoteUi
import kotlinx.android.synthetic.main.wicker_quote_details_view.*

/**
 * Created by joicemarinay on 7/1/18.
 */
internal class QuoteDetailsActivity(override val layoutResourceId: Int = R.layout.wicker_quote_details_view):
    BaseActivity<QuoteDetailsViewModel, QuoteDetailsState>() {

  private val component: QuoteDetailsComponent by lazy { QuotesDependencyHolder.detailsComponent() }

  override fun onCreate(savedInstanceState: Bundle?) {
    component.inject(this)
    super.onCreate(savedInstanceState)
    handleIntent()
    setDataObservers()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.wicker_menu_quote_details, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      android.R.id.home -> {
        onBackPressed()
        return true
      }
      R.id.wicker_menu_quotedetails_copy -> {
        viewModel.copyQuoteToClipboard(this)
        return true
      }
      R.id.wicker_menu_quotedetails_edit -> {
        viewModel.editQuote()
        return true
      }
      R.id.wicker_menu_quotedetails_delete -> {
        viewModel.deleteQuote()
        return true
      }
      else -> return super.onOptionsItemSelected(item)
    }
  }

  override fun onStateChange(state: QuoteDetailsState) = when(state) {
    is CopyFinish -> showSpielQuoteCopied()
    is OpenEditQuote -> openQuoteWriter(state.quote)
    is QuoteLoaded -> displayQuoteDetails(state.quote)
    is QuoteDetailsState.DeleteSuccessful -> quoteDeleted()
    is QuoteDetailsState.DeleteFailed -> TODO()
  }

  private fun displayQuoteDetails(quote: QuoteUi) {
    quote?.let {
      quoteDetail_text_quote.text = spannedQuote(it.quote)
      quoteDetail_text_authorAndSource.text = it.dashedAuthorAndSource
    }
  }

  private fun handleIntent() {
    viewModel.loadQuote(intent.extras.getParcelable(EXTRA_SELECTED_QUOTE) as QuoteUi)
  }

  private fun openQuoteWriter(quote: QuoteUi) {
    startActivity(QuoteWriterActivity.intentToEdit(this, quote))
  }

  private fun quoteDeleted() {
    setResult(DELETE_RESULT_OK)
    finish()
  }

  private fun setDataObservers() {
    observe(viewModel.state()) { onStateChange(it) }
  }

  /**
   * Make first character's text size larger
   *
   * TODO Find position of first alphabet
   */
  private fun spannedQuote(quote: String): SpannableString {
    val spannableString = SpannableString(quote)
    spannableString.setSpan(RelativeSizeSpan(2f), 0, 1,
      Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannableString
  }

  private fun showSpielQuoteCopied() {
    Snackbar.make(quoteDetail_parent, R.string.spiel_quote_copied, Snackbar.LENGTH_SHORT).show()
  }

  companion object {

    const val DELETE_RESULT_OK = RESULT_FIRST_USER + 1

    private val EXTRA_SELECTED_QUOTE = "$KEY_PREFIX.SELECTED_QUOTE_ID"

    fun intent(context: Context, quote: QuoteUi): Intent {
      val intent = Intent(context, QuoteDetailsActivity::class.java)
      intent.putExtra(EXTRA_SELECTED_QUOTE, quote)
      return intent
    }
  }
}