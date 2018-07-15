package io.rcm.wicker.quotes.features.details.presentation

import android.arch.lifecycle.LiveData
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
  private val resourceProvider: ResourceProvider): BaseViewModel<QuoteDetailsState>() {

  private val uiState: MediatorLiveData<QuoteDetailsState> = MediatorLiveData()
  private lateinit var quote: QuoteUi

  override fun onCleared() {
    QuotesDependencyHolder.destroyDetailsComponent()
    super.onCleared()
  }

  override fun state(): LiveData<QuoteDetailsState> = uiState

  fun copyQuoteToClipboard(context: Context) {
    quote.copyToClipBoard(context, resourceProvider)
    uiState.postValue(QuoteDetailsState.CopyFinish)
  }

  fun editQuote() {
    uiState.postValue(QuoteDetailsState.OpenEditQuote(this.quote))
  }

  fun setQuote(quote: QuoteUi) {
    this.quote = quote
    uiState.postValue(QuoteDetailsState.QuoteLoaded(this.quote))
  }

}