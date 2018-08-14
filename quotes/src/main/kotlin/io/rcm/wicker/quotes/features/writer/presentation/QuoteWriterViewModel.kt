package io.rcm.wicker.quotes.features.writer.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import io.rcm.wicker.base.presentation.BaseViewModel
import io.rcm.wicker.quotes.QuotesDependencyHolder
import io.rcm.wicker.quotes.domain.model.QuoteEntity
import io.rcm.wicker.quotes.features.writer.domain.SaveQuote
import io.rcm.wicker.quotes.presentation.QuoteUi
import javax.inject.Inject

/**
 * Created by joicemarinay on 6/24/18.
 */
internal class QuoteWriterViewModel @Inject constructor(private val saveQuote: SaveQuote,
  private val uiState: MediatorLiveData<QuoteWriterState>): BaseViewModel<QuoteWriterState>() {

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

  override fun state(): LiveData<QuoteWriterState> = uiState

  fun onQuoteInputChanged(inputtedQuote: String) {
    postSaveStateByQuoteEmptiness(inputtedQuote)
  }

  /**
   * Set default value for all params except [quote] so that it this function is also
   *  applicable to [QuoteReceiverActivity]
   */
  fun saveQuote(quote: String, author: String = "", sourceName: String = "",
    sourceUrl: String = "") {
    saveQuote.execute(QuoteEntity(id = this.quote.id, quote = quote, author = author,
      sourceName = sourceName, sourceUrl = sourceUrl))
  }

  fun setQuote(quote: QuoteUi) {
    this.quote = quote
    uiState.postValue(QuoteWriterState.EditQuote(this.quote))
  }

  private fun onSaveQuoteResult(result: SaveQuote.Result?) {
    when (result) {
      is SaveQuote.Result.OnSuccess -> uiState.postValue(QuoteWriterState.SaveSuccessful)
      is SaveQuote.Result.OnError -> uiState.postValue(QuoteWriterState.SaveFailed)
    }
  }

  private fun postSaveStateByQuoteEmptiness(quote: String) {
    uiState.postValue(QuoteWriterState.SaveState(quote.isNotEmpty()))
  }
}