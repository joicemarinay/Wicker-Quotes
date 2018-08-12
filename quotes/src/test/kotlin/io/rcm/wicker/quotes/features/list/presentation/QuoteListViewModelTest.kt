package io.rcm.wicker.quotes.features.list.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import io.rcm.wicker.quotes.common.quoteUiList
import io.rcm.wicker.quotes.domain.usecase.ChangeDeleteState
import io.rcm.wicker.quotes.domain.usecase.DeleteQuote
import io.rcm.wicker.quotes.features.list.domain.GetQuotes
import io.rcm.wicker.quotes.presentation.QuoteUi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by joicemarinay on 29/07/2018.
 */
@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
internal class QuoteListViewModelTest {

  @Rule
  @JvmField
  val instantTaskRule = InstantTaskExecutorRule()

  @Mock
  private lateinit var changeDeleteState: ChangeDeleteState
  @Mock
  private lateinit var deleteQuote: DeleteQuote
  @Mock
  private lateinit var getQuotes: GetQuotes
  @Mock
  private lateinit var quoteUi: QuoteUi
  @Mock
  private lateinit var uiStateObserver: Observer<QuoteListState>

  private val changeDeleteStateResultLiveData: MutableLiveData<ChangeDeleteState.Result> = MutableLiveData()
  private val deleteQuoteResultLiveData: MutableLiveData<DeleteQuote.Result> = MutableLiveData()
  private val getQuotesResultLiveData: MutableLiveData<GetQuotes.Result> = MutableLiveData()
  private val uiState: MediatorLiveData<QuoteListState> = MediatorLiveData()
  private lateinit var quoteListViewModel: QuoteListViewModel

  @Before
  fun setup() {
    setupLiveData()
    quoteListViewModel = QuoteListViewModel(changeDeleteState = changeDeleteState,
      deleteQuote = deleteQuote, getQuotes = getQuotes)
  }

  @After
  fun cleanup() {
    //I'm not sure if this still needs to be done but I added it anyway just to be sure there's no leak
    uiState.removeObserver(uiStateObserver)
  }

  @Test
  fun ignoreUndoDelete_shouldHardDeleteQuote() {
    whenUndoDeleteIsIgnored(quoteUi)
    thenDeleteQuoteShouldBeExecuted(quoteUi.id)
    thenDeleteQuoteShouldHaveNoMoreInteractions()
  }

  @Test
  fun loadQuotes_stateShouldBeEmpty() {
    //    whenGetQuotesIsExecuted()
    whenGetQuotesHasResult(GetQuotes.Result.OnSuccess(emptyList()))
    thenObserverShouldReceiveCorrectStates(QuoteListState.QuotesEmpty)
  }

  @Test
  fun loadQuotes_stateShouldBeError() {
    whenGetQuotesHasResult(GetQuotes.Result.OnError)
    thenObserverShouldReceiveCorrectStates(QuoteListState.GetQuotesFailed)
  }

  @Test
  fun loadQuotes_stateShouldBeLoaded() {
    whenGetQuotesHasResult(GetQuotes.Result.OnSuccess(quoteUiList))
    thenObserverShouldReceiveCorrectStates(QuoteListState.QuotesLoaded(quoteUiList))
  }

  @Test
  fun undoDelete_shouldRevertSoftDeletedQuote() {
    whenDeleteIsReverted(quoteUi)
    thenChangeDeleteStateToFalseShouldBeExecuted(quoteUi)
    thenChangeDeleteStateShouldHaveNoMoreInteractions()
  }

  private fun setupLiveData() {
    //`observeForever` method is used instead of `observe`
    //  because we don’t want the Observer to be notified
    //  depending on a lifecycle that doesn’t exist.
    //  It is a unit test and we want the Observer to be notified at any time.
    uiState.observeForever(uiStateObserver)
    given(getQuotes.liveData()).willReturn(getQuotesResultLiveData)
  }

  //<editor-fold desc="When and Then for ChangeDeleteStateUseCase">
  private fun whenDeleteIsReverted(deletedQuoteToRevert: QuoteUi) {
    changeDeleteState.execute(isSoftDeleted = false, quote = deletedQuoteToRevert)
  }

  private fun whenChangeDeleteStateHasResult(result: ChangeDeleteState.Result) {
    changeDeleteStateResultLiveData.value = result
  }

  private fun thenChangeDeleteStateToFalseShouldBeExecuted(deletedQuoteToRevert: QuoteUi) {
    then(changeDeleteState).should().execute(isSoftDeleted = false, quote = deletedQuoteToRevert)
  }

  private fun thenChangeDeleteStateShouldHaveNoMoreInteractions() {
    then(changeDeleteState).shouldHaveNoMoreInteractions()
  }
  //</editor-fold>

  //<editor-fold desc="When and Then for DeleteQuoteUseCase">
  private fun whenUndoDeleteIsIgnored(quoteToRevert: QuoteUi) {
    quoteListViewModel.ignoreUndoDelete(quoteToRevert)
  }

  private fun whenDeleteQuoteHasResult(result: DeleteQuote.Result) {
    deleteQuoteResultLiveData.value = result
  }

  private fun thenDeleteQuoteShouldBeExecuted(idOfQuoteToDelete: Int) {
    then(deleteQuote).should().execute(quoteId = idOfQuoteToDelete)
  }

  private fun thenDeleteQuoteShouldHaveNoMoreInteractions() {
    then(deleteQuote).shouldHaveNoMoreInteractions()
  }
  //</editor-fold>

  //<editor-fold desc="When and Then for GetQuotesUseCase">
  private fun whenGetQuotesIsExecuted() {
    quoteListViewModel.loadQuotes()
  }

  private fun whenGetQuotesHasResult(result: GetQuotes.Result) {
    getQuotesResultLiveData.postValue(result)
  }

  private fun thenGetQuotesShouldBeExecuted() {
    then(getQuotes).should().execute()
  }

  private fun thenGetQuotesShouldHaveNoMoreInteractions() {
    then(getQuotes).should().liveData()
    then(getQuotes).shouldHaveNoMoreInteractions()
  }
  //</editor-fold>

  private fun thenObserverShouldReceiveCorrectStates(vararg expectedUiState: QuoteListState) {
    //Maybe Mockito's [ArgumentCaptor.forClass(QuoteListState::class.java)] can also be used here
    expectedUiState.forEach { then(uiStateObserver).should().onChanged(it) }
    then(uiStateObserver).shouldHaveNoMoreInteractions()
  }

}