package io.rcm.wicker.quotes.features.details.presentation

import android.arch.lifecycle.MutableLiveData
import io.rcm.wicker.base.presentation.BaseViewModel
import io.rcm.wicker.quotes.QuotesDependencyHolder
import io.rcm.wicker.quotes.presentation.QuoteUi
import javax.inject.Inject

/**
 * Created by joicemarinay on 7/1/18.
 */
internal class QuoteDetailsViewModel @Inject constructor(): BaseViewModel() {

  val quote: MutableLiveData<QuoteUi> = MutableLiveData<QuoteUi>()

  override fun onCleared() {
    QuotesDependencyHolder.destroyDetailsComponent()
    super.onCleared()
  }

  fun setQuote(quote: QuoteUi) {
    this.quote.postValue(quote)
  }

}