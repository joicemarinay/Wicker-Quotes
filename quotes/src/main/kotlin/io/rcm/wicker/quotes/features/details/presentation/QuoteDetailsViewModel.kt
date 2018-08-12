package io.rcm.wicker.quotes.features.details.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.content.Context
import io.rcm.wicker.base.analytics.AnalyticsEvent
import io.rcm.wicker.base.analytics.AnalyticsTool
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
internal class QuoteDetailsViewModel @Inject constructor(private val analytics: AnalyticsTool,
  private val changeDeleteState: ChangeDeleteState, private val getQuoteDetails: GetQuoteDetails,
  private val resourceProvider: ResourceProvider): BaseViewModel<QuoteDetailsState>() {

  private val uiState: MediatorLiveData<QuoteDetailsState> = MediatorLiveData()
  private lateinit var quote: QuoteUi

  init {
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
    analytics.sendEvent(AnalyticsEvent.Feature.QUOTES, "copy")
    quote.copyToClipBoard(context, resourceProvider)
    uiState.postValue(QuoteDetailsState.CopyFinish)
  }

  /**
   * Initially, adding [changeDeleteState]'s LiveData as one of [uiState]'s source is done in
   *  [QuoteDetailsViewModel.init]. But I noticed that the next time this ViewModel's
   *  LifecycleOwner is created, [onDeleteQuoteResult] will be called even when
   *  [changeDeleteState] is not yet executed. Because of that, I decided to only add
   *  [changeDeleteState]'s LiveData as [uiState]'s source when deletion happens as a safe measure.
   *
   * Note: I've read a bit about googlesample's SingleLiveEvent. It seems like a solution to
   *  this problem but I still need to understand its use case further.
   *  @see https://github.com/googlesamples/android-architecture/blob/dev-todo-mvvm-live/todoapp/app/src/main/java/com/example/android/architecture/blueprints/todoapp/SingleLiveEvent.java
   *  @see https://medium.com/google-developers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
   */
  fun deleteQuote() {
    uiState.addSource(changeDeleteState.liveData(), ::onDeleteQuoteResult)
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

  /**
   * Remove [changeDeleteState]'s LiveData as one of [uiState]'s source when change is already
   *  received to prevent unexpected emission of result.
   *
   * See comments on [deleteQuote] for context of problem
   */
  private fun onDeleteQuoteResult(result: ChangeDeleteState.Result?) {
    Timber.d("onDeleteQuoteResult() $result")
    uiState.removeSource(changeDeleteState.liveData())
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

  /**
   * Checking quote is deleted before setting [uiState] as [QuoteDetailsState.QuoteLoaded]
   *  prevents overriding [uiState] value of [QuoteDetailsState.DeleteSuccessful]
   *   when a quote is deleted. Without this check, there will be times when the LifeCycleOwner
   *   that listens to uiState will miss the uiState change to [QuoteDetailsState.DeleteSuccessful]
   */
  private fun setQuote(quote: QuoteUi) {
    this.quote = quote
    if (!quote.isDeleted) {
      uiState.postValue(QuoteDetailsState.QuoteLoaded(this.quote))
    }
  }

}