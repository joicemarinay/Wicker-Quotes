package io.rcm.wicker.quotes.features.details.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import io.rcm.wicker.base.common.KEY_PREFIX
import io.rcm.wicker.base.common.observe
import io.rcm.wicker.base.presentation.BaseActivity
import io.rcm.wicker.quotes.QuotesDependencyHolder
import io.rcm.wicker.quotes.R
import io.rcm.wicker.quotes.features.details.injection.QuoteDetailsComponent
import io.rcm.wicker.quotes.presentation.QuoteUi
import kotlinx.android.synthetic.main.wicker_quote_details_view.*

/**
 * Created by joicemarinay on 7/1/18.
 */
internal class QuoteDetailsActivity(override val layoutResourceId: Int = R.layout.wicker_quote_details_view):
    BaseActivity<QuoteDetailsViewModel>() {

  private val component: QuoteDetailsComponent by lazy { QuotesDependencyHolder.detailsComponent() }

  override fun onCreate(savedInstanceState: Bundle?) {
    component.inject(this)
    super.onCreate(savedInstanceState)
    handleIntent()
    setDataObservers()
  }

  private fun handleIntent() {
    viewModel.setQuote(intent.extras.getParcelable(EXTRA_SELECTED_QUOTE) as QuoteUi)
  }

  private fun onQuoteChanged(quote: QuoteUi) {
    quote?.let {
      quoteDetail_text_quote.text = spannedQuote(it.quote)
      quoteDetail_text_authorAndSource.text = it.dashedAuthorAndSource
    }
  }

  private fun setDataObservers() {
    observe(viewModel.quote) { onQuoteChanged(it) }
  }

  /**
   * Make first character's text size larger
   *
   * TODO Find position of first alphabet
   */
  private fun spannedQuote(quote: String): SpannableString {
    val spannableString = SpannableString(quote);
    spannableString.setSpan(RelativeSizeSpan(2f), 0, 1,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannableString
  }

  companion object {

    private val EXTRA_SELECTED_QUOTE = "$KEY_PREFIX.SELECTED_QUOTE"

    fun intent(context: Context, quote: QuoteUi): Intent {
      val intent = Intent(context, QuoteDetailsActivity::class.java)
      intent.putExtra(EXTRA_SELECTED_QUOTE, quote)
      return intent
    }
  }
}