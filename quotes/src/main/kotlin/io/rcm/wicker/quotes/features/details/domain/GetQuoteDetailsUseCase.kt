package io.rcm.wicker.quotes.features.details.domain

import io.rcm.wicker.base.domain.BaseUseCase
import io.rcm.wicker.quotes.domain.QuotesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import io.rcm.wicker.quotes.domain.model.QuoteEntity
import io.rcm.wicker.quotes.presentation.QuotesUiMapper
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by joicemarinay on 15/07/2018.
 */
internal class GetQuoteDetailsUseCase @Inject constructor(private val repo: QuotesRepository,
  private val mapper: QuotesUiMapper): BaseUseCase<GetQuoteDetails.Result>(), GetQuoteDetails {

  override fun execute(quoteId: Int) {
    repo.getQuote(quoteId)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(::success, ::error)
      .track()
  }

  private fun success(quote: QuoteEntity) {
    liveData.value = GetQuoteDetails.Result.OnSuccess(mapper.mapFromDomain(quote))
  }

  private fun error(throwable: Throwable) {
    Timber.e(throwable)
    liveData.value = GetQuoteDetails.Result.OnError
  }

}