package io.rcm.wicker.quotes.features.details.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.content.Context
import io.rcm.wicker.base.presentation.BaseViewModel
import io.rcm.wicker.quotes.QuotesDependencyHolder
import io.rcm.wicker.quotes.domain.usecase.ChangeDeleteState
import io.rcm.wicker.quotes.features.details.domain.GetQuoteDetails
import io.rcm.wicker.quotes.presentation.QuoteUi
import io.rcm.wicker.quotes.presentation.ResourceProvider
import io.rcm.wicker.quotes.presentation.copyToClipBoard
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by joicemarinay on 7/1/18.
 */
internal class QuoteDetailsViewModel @Inject constructor(
  private val changeDeleteState: ChangeDeleteState, private val getQuoteDetails: GetQuoteDetails,
  private val resourceProvider: ResourceProvider): BaseViewModel<QuoteDetailsState>() {

  private val uiState: MediatorLiveData<QuoteDetailsState> = MediatorLiveData()
  private lateinit var quote: QuoteUi

  init {
    uiState.addSource(changeDeleteState.liveData(), ::onDeleteQuoteResult)
    uiState.addSource(getQuoteDetails.liveData(), ::onGetQuoteDetailsResult)
  }

  override fun onCleared() {
    changeDeleteState.cleanUp()
    getQuoteDetails.cleanUp()
    QuotesDependencyHolder.destroyDetailsComponent()
    super.onCleared()
  }

  override fun state(): LiveData<QuoteDetailsState> = uiState

  fun copyQuoteToClipboard(context: Context) {
    quote.copyToClipBoard(context, resourceProvider)
    uiState.postValue(QuoteDetailsState.CopyFinish)
  }

  fun deleteQuote() {
    changeDeleteState.execute(quote, true)
  }

  fun editQuote() {
    uiState.postValue(QuoteDetailsState.OpenEditQuote(this.quote))
  }

  /**
   * `getQuoteDetails.execute(quote.id)`
   *  - get details of quote from data source.
   *  This also allows this ViewModel to subscribe to any changes made on this quote
   *    (e.g. when editing this quote, the changes will be reflected right away)
   *
   * `setQuote(quote)`
   *  - Display quote right away. If this is removed, a "flicker" will be displayed in UI
   *    since there is an delay/interval to get quote details from data source
   *    before being able to display it
   */
  fun loadQuote(quote: QuoteUi) {
    getQuoteDetails.execute(quote.id)
    setQuote(quote)
  }

  private fun onDeleteQuoteResult(result: ChangeDeleteState.Result?) {
    Timber.d("onDeleteQuoteResult() $result")
    when (result) {
      is ChangeDeleteState.Result.OnSuccess ->
        uiState.postValue(QuoteDetailsState.DeleteSuccessful(this.quote))
      is ChangeDeleteState.Result.OnError -> uiState.postValue(QuoteDetailsState.DeleteFailed)
    }
  }

  private fun onGetQuoteDetailsResult(result: GetQuoteDetails.Result?) {
    when (result) {
      is GetQuoteDetails.Result.OnError -> Timber.d("onGetQuoteDetailsResult() failed") //TODO
      is GetQuoteDetails.Result.OnSuccess -> setQuote(result.quote)
    }
  }

  private fun setQuote(quote: QuoteUi) {
    this.quote = quote
    uiState.postValue(QuoteDetailsState.QuoteLoaded(this.quote))
  }

}