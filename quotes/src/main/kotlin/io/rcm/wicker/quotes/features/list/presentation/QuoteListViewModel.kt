package io.rcm.wicker.quotes.features.list.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import io.rcm.wicker.base.presentation.BaseViewModel
import io.rcm.wicker.quotes.QuotesDependencyHolder
import io.rcm.wicker.quotes.domain.usecase.ChangeDeleteState
import io.rcm.wicker.quotes.domain.usecase.DeleteQuote
import io.rcm.wicker.quotes.features.list.domain.GetQuotes
import io.rcm.wicker.quotes.presentation.QuoteUi
import javax.inject.Inject

/**
 * Created by joicemarinay on 6/24/18.
 */
internal class QuoteListViewModel @Inject constructor(private val getQuotes: GetQuotes,
  private val changeDeleteState: ChangeDeleteState, private val deleteQuote: DeleteQuote):
  BaseViewModel<QuoteListState>() {

  private val uiState: MediatorLiveData<QuoteListState> = MediatorLiveData()

  init {
    uiState.addSource(getQuotes.liveData(), ::onGetQuotesResult)
  }

  //STUDY why destroy component in ViewModel.onCleared() instead of in Activity.onDestroy()
  override fun onCleared() {
    changeDeleteState.cleanUp()
    getQuotes.cleanUp()
    QuotesDependencyHolder.destroyListComponent()
    super.onCleared()
  }

  override fun state(): LiveData<QuoteListState> = uiState

  /**
   * Permanently delete quote when option to undo deletion is ignored
   */
  fun ignoreUndoDelete(deletedQuote: QuoteUi) {
    deleteQuote.execute(deletedQuote.id)
  }

  fun loadQuotes() {
    uiState.postValue(QuoteListState.Loading)
    getQuotes.execute()
  }

  fun undoDelete(deletedQuote: QuoteUi) {
    changeDeleteState.execute(deletedQuote, false)
  }

  private fun onGetQuotesResult(result: GetQuotes.Result?) {
    when (result) {
      is GetQuotes.Result.OnError -> uiState.postValue(QuoteListState.GetQuotesFailed)
      is GetQuotes.Result.OnSuccess -> onGetQuotesResultSuccess(result.quotes)
    }
  }

  private fun onGetQuotesResultSuccess(quotes: List<QuoteUi>) {
    if (quotes.isEmpty()) {
      uiState.postValue(QuoteListState.QuotesEmpty)
    } else {
      uiState.postValue(QuoteListState.QuotesLoaded(quotes))
    }
  }
}