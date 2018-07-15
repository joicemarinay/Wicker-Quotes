package io.rcm.wicker.quotes.features.writer.presentation

import io.rcm.wicker.base.presentation.BaseUiState
import io.rcm.wicker.quotes.presentation.QuoteUi

/**
 * Created by joicemarinay on 15/07/2018.
 */
internal sealed class QuoteWriterState: BaseUiState() {
  data class EditQuote(val quote: QuoteUi): QuoteWriterState()
  object Loading: QuoteWriterState()
  object SaveSuccessful: QuoteWriterState()
  object SaveFailed: QuoteWriterState()
}