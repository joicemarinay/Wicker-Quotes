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

  /**
   * This rule will make sure that writing of the LiveData's value
   * is synchronous instead of asynchronous
   */
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

  private val getQuotesResultLiveData: MutableLiveData<GetQuotes.Result> = MutableLiveData()
  private val uiState: MediatorLiveData<QuoteListState> = MediatorLiveData()

  private lateinit var quoteListViewModel: QuoteListViewModel

  @Before
  fun setup() {
    setupLiveData()
    quoteListViewModel = QuoteListViewModel(changeDeleteState = changeDeleteState,
      deleteQuote = deleteQuote, getQuotes = getQuotes, uiState = uiState)
  }

  @After
  fun cleanup() {
    //I'm not sure if this still needs to be done but I added it anyway just to be sure
    uiState.removeObserver(uiStateObserver)
  }

  @Test
  fun ignoreUndoDelete_shouldHardDeleteQuote() {
    //Given a soft-deleted quote
    val softDeletedQuote: QuoteUi = quoteUi

    //When undo delete is ignored for soft-deleted quote
    quoteListViewModel.ignoreUndoDelete(softDeletedQuote)

    //Then delete quote should be executed
    then(deleteQuote).should().execute(quoteId = softDeletedQuote.id)
    //and delete quote use case should have no more interactions
    then(deleteQuote).shouldHaveNoMoreInteractions()
  }

  @Test
  fun loadQuotesFailed_stateShouldBeError() {
    whenGetQuotesHasResult(GetQuotes.Result.OnError)
    thenObserverShouldReceiveCorrectStates(QuoteListState.GetQuotesFailed)
    thenGetQuotesShouldHaveNoMoreInteractions()
  }

  @Test
  fun loadQuotesReturnEmptyList_stateShouldBeEmpty() {
    whenGetQuotesHasResult(GetQuotes.Result.OnSuccess(emptyList()))
    thenObserverShouldReceiveCorrectStates(QuoteListState.QuotesEmpty)
    thenGetQuotesShouldHaveNoMoreInteractions()
  }

  @Test
  fun loadQuotesReturnList_stateShouldBeLoaded() {
    whenGetQuotesHasResult(GetQuotes.Result.OnSuccess(quoteUiList))
    thenObserverShouldReceiveCorrectStates(QuoteListState.QuotesLoaded(quoteUiList))
    thenGetQuotesShouldHaveNoMoreInteractions()
  }

  @Test
  fun loadQuotesExecuted_stateShouldBeLoading() {
    whenGetQuotesIsExecuted()
    thenObserverShouldReceiveCorrectStates(QuoteListState.Loading)
    thenGetQuotesShouldBeExecuted()
    thenGetQuotesShouldHaveNoMoreInteractions()
  }

  @Test
  fun undoDelete_shouldRevertDeletionOfQuote() {
    //Given a soft-deleted quote
    val softDeletedQuote: QuoteUi = quoteUi

    //When undo delete is called
    quoteListViewModel.undoDelete(softDeletedQuote)

    //Then change delete state should be executed
    then(changeDeleteState).should().execute(quote = softDeletedQuote, isSoftDeleted = false)

    //and change delete state use case should have no more interactions
    then(changeDeleteState).shouldHaveNoMoreInteractions()
  }

  private fun setupLiveData() {
    //`observeForever` method is used instead of `observe`
    //  because we don’t want the Observer to be notified
    //  depending on a lifecycle that doesn’t exist.
    //  It is a unit test and we want the Observer to be notified at any time.
    uiState.observeForever(uiStateObserver)
    given(getQuotes.liveData()).willReturn(getQuotesResultLiveData)
  }

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
  }

}