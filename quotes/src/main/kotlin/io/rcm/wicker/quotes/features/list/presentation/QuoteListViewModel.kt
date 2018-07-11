package io.rcm.wicker.quotes.features.list.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import io.rcm.wicker.base.presentation.BaseViewModel
import io.rcm.wicker.quotes.QuotesDependencyHolder
import io.rcm.wicker.quotes.features.list.domain.GetQuotes
import io.rcm.wicker.quotes.presentation.QuoteUi
import javax.inject.Inject

/**
 * Created by joicemarinay on 6/24/18.
 */
internal class QuoteListViewModel @Inject constructor(private val getQuotes: GetQuotes):
  BaseViewModel() {

  private val uiState: MediatorLiveData<QuoteListViewModel.UiState> = MediatorLiveData()

  init {
    uiState.addSource(getQuotes.liveData(), ::onGetQuotesResult)
    fetchQuotes()
  }

  //STUDY why destroy component in ViewModel.onCleared() instead of in Activity.onDestroy()
  override fun onCleared() {
    getQuotes.cleanUp()
    QuotesDependencyHolder.destroyListComponent()
    super.onCleared()
  }

  fun state(): LiveData<UiState> = uiState

  private fun fetchQuotes() {
    uiState.value = UiState.ShowLoading
    getQuotes.execute()
  }

  private fun onGetQuotesResult(result: GetQuotes.Result?) {
    when (result) {
      is GetQuotes.Result.OnError -> uiState.value = UiState.GetQuotesFailed
      is GetQuotes.Result.OnSuccess -> onGetQuotesResultSuccess(result.quotes)
    }
  }

  private fun onGetQuotesResultSuccess(quotes: List<QuoteUi>) {
    if (quotes.isEmpty()) {
      uiState.value = UiState.GetQuotesOkEmpty
    } else {
      uiState.value = UiState.GetQuotesOk
      val quotesLoaded = UiState.QuotesLoaded(quotes)
      uiState.value = quotesLoaded
    }
  }

  sealed class UiState {
    data class QuotesLoaded(val quotes: List<QuoteUi>): UiState()
    object GetQuotesOk: UiState()
    object GetQuotesOkEmpty: UiState()
    object GetQuotesFailed: UiState()
    object ShowLoading: UiState()

  }
}