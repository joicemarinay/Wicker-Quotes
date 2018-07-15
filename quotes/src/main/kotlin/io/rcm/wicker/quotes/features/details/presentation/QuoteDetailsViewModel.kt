package io.rcm.wicker.quotes.features.details.presentation

import android.arch.lifecycle.MediatorLiveData
import android.content.Context
import io.rcm.wicker.base.presentation.BaseViewModel
import io.rcm.wicker.quotes.QuotesDependencyHolder
import io.rcm.wicker.quotes.presentation.QuoteUi
import io.rcm.wicker.quotes.presentation.ResourceProvider
import io.rcm.wicker.quotes.presentation.copyToClipBoard
import javax.inject.Inject

/**
 * Created by joicemarinay on 7/1/18.
 */
internal class QuoteDetailsViewModel @Inject constructor(
  private val resourceProvider: ResourceProvider):
  BaseViewModel() {

  val uiState: MediatorLiveData<UiState> = MediatorLiveData()
  private lateinit var quote: QuoteUi

  override fun onCleared() {
    QuotesDependencyHolder.destroyDetailsComponent()
    super.onCleared()
  }

  fun copyQuoteToClipboard(context: Context) {
    quote.copyToClipBoard(context, resourceProvider)
    uiState.postValue(UiState.CopyFinish)
  }

  fun editQuote() {
    uiState.postValue(UiState.OpenEditQuote(this.quote))
  }

  fun setQuote(quote: QuoteUi) {
    this.quote = quote
    uiState.postValue(UiState.QuoteLoaded(this.quote))
  }

  sealed class UiState {
    data class OpenEditQuote(val quote: QuoteUi): UiState()
    data class QuoteLoaded(val quote: QuoteUi): UiState()
    object CopyFinish: UiState()
  }

}