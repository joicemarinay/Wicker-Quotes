package io.rcm.wicker.quotes.features.list.domain

import android.arch.core.executor.testing.InstantTaskExecutorRule
import io.rcm.wicker.base.common.SchedulerRule
import io.rcm.wicker.quotes.common.quoteEntityList
import io.rcm.wicker.quotes.common.quoteUiList
import io.rcm.wicker.quotes.domain.QuotesRepository
import io.rcm.wicker.quotes.domain.model.QuoteEntity
import io.rcm.wicker.quotes.presentation.QuoteUi
import io.rcm.wicker.quotes.presentation.QuotesUiMapper
import io.reactivex.Flowable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by joicemarinay on 29/07/2018.
 */
@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
internal class GetQuotesUseCaseTest {

  @Rule
  @JvmField
  val instantTaskRule = InstantTaskExecutorRule()
  @Rule
  @JvmField
  val schedulerRule = SchedulerRule()

  @Mock
  private lateinit var mapper: QuotesUiMapper
  @Mock
  private lateinit var repo: QuotesRepository
  @Mock
  private lateinit var throwable: Throwable

  private lateinit var getQuotes: GetQuotesUseCase

  @Before
  fun setup() {
    getQuotes = GetQuotesUseCase(mapper = mapper, repo = repo)
  }

  @Test
  fun execute_resultShouldBeError() {
    givenRepository(Flowable.error(throwable))
    whenUseCaseIsExecuted()
    thenLiveDataShouldHaveCorrectValue(GetQuotes.Result.OnError)
  }

  @Test
  fun execute_resultShouldBeSuccess() {
    givenRepository(Flowable.just(quoteEntityList))
    givenMapping(quoteUiList)
    whenUseCaseIsExecuted()
    thenLiveDataShouldHaveCorrectValue(GetQuotes.Result.OnSuccess(quoteUiList))
  }

  private fun givenMapping(quoteUiList: List<QuoteUi>) {
    given(mapper.mapFromDomain(quoteEntityList)).willReturn(quoteUiList)
  }

  private fun givenRepository(flowableQuotes: Flowable<List<QuoteEntity>>) {
    given(repo.getAll()).willReturn(flowableQuotes)
  }

  private fun whenUseCaseIsExecuted() {
    getQuotes.execute()
  }

  private fun thenLiveDataShouldHaveCorrectValue(expectedResult: GetQuotes.Result) {
    assertEquals(expectedResult, getQuotes.liveData().value)
  }
}