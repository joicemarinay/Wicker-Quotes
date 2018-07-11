package io.rcm.wicker.quotes.features.writer.presentation

import android.os.Bundle
import io.rcm.wicker.base.common.observe
import io.rcm.wicker.base.presentation.BaseActivity
import io.rcm.wicker.quotes.QuotesDependencyHolder
import io.rcm.wicker.quotes.R
import io.rcm.wicker.quotes.features.writer.injection.QuoteWriterComponent
import io.rcm.wicker.quotes.features.writer.presentation.QuoteWriterViewModel.UiState
import kotlinx.android.synthetic.main.wicker_quote_writer_view.*

/**
 * Created by joicemarinay on 09/05/2018.
 *
 * This Activity is not internal because it is used by
 * [io.rcm.wicker.app.view.ScreenRouterImpl]
 */
internal class QuoteWriterActivity(override val layoutResourceId: Int = R.layout.wicker_quote_writer_view):
    BaseActivity<QuoteWriterViewModel>() {

  private val component: QuoteWriterComponent by lazy { QuotesDependencyHolder.writerComponent() }

  override fun onCreate(savedInstanceState: Bundle?) {
    component.inject(this)
    super.onCreate(savedInstanceState)
    setClickListeners()
    setDataObservers()
  }

  private fun inputAuthor(): String = quoteWriter_editText_author.text.toString()

  private fun inputLink(): String = quoteWriter_editText_link.text.toString()

  private fun inputQuote(): String = quoteWriter_editText_quote.text.toString()

  private fun inputSource(): String = quoteWriter_editText_source.text.toString()

  private fun onStateChange(uiState: UiState) = when(uiState) {
    is UiState.Loading -> showLoading()
    UiState.SaveFailed -> showError()
    UiState.SaveOk -> finish()
  }

  private fun setClickListeners() {
    quoteWriter_button_close.setOnClickListener { onBackPressed() }
    quoteWriter_button_save.setOnClickListener { viewModel.onClickSave(quote = inputQuote(),
      author = inputAuthor(), sourceName = inputSource(), sourceUrl = inputLink()) }
  }

  private fun setDataObservers() {
    observe(viewModel.state()) { onStateChange(it) }
  }

  private fun showError() {
    //TODO
  }

  private fun showLoading() {
    //TODO display loading
  }
}