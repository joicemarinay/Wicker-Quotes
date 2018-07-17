package io.rcm.wicker.quotes.features.details.presentation

import io.rcm.wicker.base.presentation.BaseUiState
import io.rcm.wicker.quotes.presentation.QuoteUi

/**
 * Created by joicemarinay on 15/07/2018.
 */
internal sealed class QuoteDetailsState: BaseUiState() {
  data class OpenEditQuote(val quote: QuoteUi): QuoteDetailsState()
  data class QuoteLoaded(val quote: QuoteUi): QuoteDetailsState()
  object CopyFinish: QuoteDetailsState()
  object DeleteSuccessful: QuoteDetailsState()
  object DeleteFailed: QuoteDetailsState()
}