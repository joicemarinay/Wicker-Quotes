package io.rcm.wicker.quotes.writer.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import io.rcm.wicker.base.presentation.BaseViewModel
import io.rcm.wicker.quotes.QuotesDependencyHolder
import io.rcm.wicker.quotes.domain.model.QuoteEntity
import io.rcm.wicker.quotes.writer.domain.SaveQuote
import javax.inject.Inject

/**
 * Created by joicemarinay on 6/24/18.
 */
internal class QuoteWriterViewModel @Inject constructor(private val saveQuote: SaveQuote):
  BaseViewModel() {

  private val state: MediatorLiveData<State> = MediatorLiveData()

  init {
    /**
     * Start listening to [saveQuote.liveData()]
     *  then call [onSaveQuoteResult(SaveQuote.Result?)] whenever saveQuote.liveData().value changes
     */
    state.addSource(saveQuote.liveData(), ::onSaveQuoteResult)
  }

  //STUDY why destroy component in ViewModel.onCleared() instead of in Activity.onDestroy()
  override fun onCleared() {
    saveQuote.cleanUp()
    QuotesDependencyHolder.destroyWriterComponent()
    super.onCleared()
  }

  //TODO set quote as mandatory
  fun onClickSave(quote: String, author: String, sourceName: String, sourceUrl: String) {
    saveQuote.execute(QuoteEntity(quote, author, sourceName, sourceUrl))
  }

  fun state(): LiveData<State> = state

  private fun onSaveQuoteResult(result: SaveQuote.Result?) {
    when (result) {
      is SaveQuote.Result.OnSuccess -> state.value = State.SaveOk
      is SaveQuote.Result.OnError -> state.value = State.SaveFailed
    }
  }

  sealed class State {
    object Loading : State()
    object SaveOk: State()
    object SaveFailed: State()
  }
}