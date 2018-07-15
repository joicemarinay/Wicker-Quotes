package io.rcm.wicker.quotes.features.list.presentation

import io.rcm.wicker.base.presentation.BaseUiState
import io.rcm.wicker.quotes.presentation.QuoteUi

/**
 * Created by joicemarinay on 15/07/2018.
 */
internal sealed class QuoteListState: BaseUiState() {
  data class QuotesLoaded(val quotes: List<QuoteUi>): QuoteListState()
  object GetQuotesSuccessful: QuoteListState()
  object GetQuotesFailed: QuoteListState()
  object Loading: QuoteListState()
  object QuotesEmpty: QuoteListState()

}