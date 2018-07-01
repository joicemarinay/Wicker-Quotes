package io.rcm.wicker.quotes.list.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import io.rcm.wicker.base.presentation.BaseViewModel
import io.rcm.wicker.quotes.QuotesDependencyHolder
import io.rcm.wicker.quotes.list.domain.GetQuotes
import io.rcm.wicker.quotes.presentation.QuoteUi
import javax.inject.Inject

/**
 * Created by joicemarinay on 6/24/18.
 */
internal class QuoteListViewModel @Inject constructor(private val getQuotes: GetQuotes):
  BaseViewModel() {

  private val state: MediatorLiveData<QuoteListViewModel.State> = MediatorLiveData()

  init {
    state.addSource(getQuotes.liveData(), ::onGetQuotesResult)
    fetchQuotes()
  }

  //STUDY why destroy component in ViewModel.onCleared() instead of in Activity.onDestroy()
  override fun onCleared() {
    getQuotes.cleanUp()
    QuotesDependencyHolder.destroyListComponent()
    super.onCleared()
  }

  fun state(): LiveData<State> = state

  private fun fetchQuotes() {
    state.value = State.ShowLoading
    getQuotes.execute()
  }

  private fun onGetQuotesResult(result: GetQuotes.Result?) {
    when (result) {
      is GetQuotes.Result.OnError -> state.value = State.GetQuotesFailed
      is GetQuotes.Result.OnSuccess -> onGetQuotesResultSuccess(result.quotes)
    }
  }

  private fun onGetQuotesResultSuccess(quotes: List<QuoteUi>) {
    if (quotes.isEmpty()) {
      state.value = State.GetQuotesOkEmpty
    } else {
      state.value = State.GetQuotesOk
      val quotesLoaded = State.QuotesLoaded(quotes)
      state.value = quotesLoaded
    }
  }

  sealed class State {
    data class QuotesLoaded(val quotes: List<QuoteUi>): State()
    object GetQuotesOk: State()
    object GetQuotesOkEmpty: State()
    object GetQuotesFailed: State()
    object ShowLoading: State()

  }
}