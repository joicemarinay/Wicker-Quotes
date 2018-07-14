package io.rcm.wicker.quotes.features.details.presentation

import android.arch.lifecycle.MutableLiveData
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
  private val resourceProvider: ResourceProvider): BaseViewModel() {

  val onCopySuccess: MutableLiveData<Boolean> = MutableLiveData()
  val quote: MutableLiveData<QuoteUi> = MutableLiveData()

  override fun onCleared() {
    QuotesDependencyHolder.destroyDetailsComponent()
    super.onCleared()
  }

  fun copyQuoteToClipboard(context: Context) {
    quote.value?.copyToClipBoard(context, resourceProvider)
    onCopySuccess.postValue(true)
  }

  fun setQuote(quote: QuoteUi) {
    this.quote.postValue(quote)
  }

}