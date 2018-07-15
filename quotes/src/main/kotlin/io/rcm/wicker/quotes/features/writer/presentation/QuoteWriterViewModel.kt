package io.rcm.wicker.quotes.features.writer.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import io.rcm.wicker.base.presentation.BaseViewModel
import io.rcm.wicker.quotes.QuotesDependencyHolder
import io.rcm.wicker.quotes.domain.model.QuoteEntity
import io.rcm.wicker.quotes.features.writer.domain.SaveQuote
import io.rcm.wicker.quotes.presentation.QuoteUi
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by joicemarinay on 6/24/18.
 */
internal class QuoteWriterViewModel @Inject constructor(private val saveQuote: SaveQuote):
  BaseViewModel() {

  private val uiState: MediatorLiveData<UiState> = MediatorLiveData()
  private var quote: QuoteUi = QuoteUi.empty()

  init {
    /**
     * Start listening to [saveQuote.liveData()]
     *  then call [onSaveQuoteResult(SaveQuote.Result?)] whenever saveQuote.liveData().value changes
     */
    uiState.addSource(saveQuote.liveData(), ::onSaveQuoteResult)
  }

  //STUDY why destroy component in ViewModel.onCleared() instead of in Activity.onDestroy()
  override fun onCleared() {
    saveQuote.cleanUp()
    QuotesDependencyHolder.destroyWriterComponent()
    super.onCleared()
  }

  //TODO set quote as mandatory
  fun onClickSave(quote: String, author: String, sourceName: String, sourceUrl: String) {
    saveQuote.execute(QuoteEntity(id = this.quote.id, quote = quote, author = author,
      sourceName = sourceName, sourceUrl = sourceUrl))
  }

  fun setQuote(quote: QuoteUi) {
    this.quote = quote
    uiState.postValue(UiState.EditQuote(this.quote))
  }

  fun state(): LiveData<UiState> = uiState

  private fun onSaveQuoteResult(result: SaveQuote.Result?) {
    when (result) {
      is SaveQuote.Result.OnSuccess -> uiState.value = UiState.SaveOk
      is SaveQuote.Result.OnError -> uiState.value = UiState.SaveFailed
    }
  }

  sealed class UiState {
    data class EditQuote(val quote: QuoteUi): UiState()
    object Loading : UiState()
    object SaveOk: UiState()
    object SaveFailed: UiState()
  }
}