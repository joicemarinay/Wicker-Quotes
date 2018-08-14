package io.rcm.wicker.quotes.features.writer.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.rcm.wicker.base.common.KEY_PREFIX
import io.rcm.wicker.base.common.observe
import io.rcm.wicker.base.common.setTextChangeListener
import io.rcm.wicker.base.presentation.BaseActivity
import io.rcm.wicker.quotes.QuotesDependencyHolder
import io.rcm.wicker.quotes.R
import io.rcm.wicker.quotes.features.writer.injection.QuoteWriterComponent
import io.rcm.wicker.quotes.features.writer.presentation.QuoteWriterState.EditQuote
import io.rcm.wicker.quotes.features.writer.presentation.QuoteWriterState.Loading
import io.rcm.wicker.quotes.features.writer.presentation.QuoteWriterState.SaveFailed
import io.rcm.wicker.quotes.features.writer.presentation.QuoteWriterState.SaveState
import io.rcm.wicker.quotes.features.writer.presentation.QuoteWriterState.SaveSuccessful
import io.rcm.wicker.quotes.presentation.QuoteUi
import kotlinx.android.synthetic.main.wicker_quote_writer_view.*

/**
 * Created by joicemarinay on 09/05/2018.
 *
 * This Activity is not internal because it is used by
 * [io.rcm.wicker.app.view.ScreenRouterImpl]
 */
internal class QuoteWriterActivity(override val layoutResourceId: Int = R.layout.wicker_quote_writer_view):
    BaseActivity<QuoteWriterViewModel, QuoteWriterState>() {

  private val component: QuoteWriterComponent by lazy { QuotesDependencyHolder.writerComponent() }

  override fun onCreate(savedInstanceState: Bundle?) {
    component.inject(this)
    super.onCreate(savedInstanceState)
    handleIntent()
    setActionListeners()
    setClickListeners()
    setDataObservers()
    setSaveButton(false)
  }

  override fun onStateChange(state: QuoteWriterState) = when(state) {
    is EditQuote -> prefillFields(state.quote)
    is Loading -> showLoading()
    is SaveFailed -> showError()
    is SaveSuccessful -> finish()
    is SaveState -> setSaveButton(state.isSaveEnabled)
  }

  private fun handleIntent() {
    intent.extras?.let { viewModel.setQuote(it.getParcelable(EXTRA_QUOTE_TO_EDIT) as QuoteUi) }
  }

  private fun inputAuthor(): String = quoteWriter_editText_author.text.toString()

  private fun inputLink(): String = quoteWriter_editText_link.text.toString()

  private fun inputQuote(): String = quoteWriter_editText_quote.text.toString()

  private fun inputSource(): String = quoteWriter_editText_source.text.toString()

  private fun prefillFields(quote: QuoteUi) {
    quoteWriter_editText_author.setText(quote.author)
    quoteWriter_editText_link.setText(quote.sourceUrl)
    quoteWriter_editText_quote.setText(quote.quote)
    quoteWriter_editText_source.setText(quote.sourceName)
  }

  private fun setActionListeners() {
    quoteWriter_editText_quote.setTextChangeListener { viewModel.onQuoteInputChanged(it) }
  }

  private fun setClickListeners() {
    quoteWriter_button_close.setOnClickListener { onBackPressed() }
    quoteWriter_button_save.setOnClickListener { viewModel.saveQuote(quote = inputQuote(),
      author = inputAuthor(), sourceName = inputSource(), sourceUrl = inputLink()) }
  }

  private fun setDataObservers() {
    observe(viewModel.state()) { onStateChange(it) }
  }

  private fun setSaveButton(isEnabled: Boolean) {
    quoteWriter_button_save.isEnabled = isEnabled
  }

  private fun showError() {
    //TODO
  }

  private fun showLoading() {
    //TODO display loading
  }

  companion object {
    private val EXTRA_QUOTE_TO_EDIT = "$KEY_PREFIX.QUOTE_TO_EDIT"

    fun intentToEdit(context: Context, quote: QuoteUi): Intent {
      val intent = Intent(context, QuoteWriterActivity::class.java)
      intent.putExtra(EXTRA_QUOTE_TO_EDIT, quote)
      return intent
    }
  }
}