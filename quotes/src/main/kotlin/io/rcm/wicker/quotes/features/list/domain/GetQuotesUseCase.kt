package io.rcm.wicker.quotes.features.list.domain

import io.rcm.wicker.base.domain.BaseUseCase
import io.rcm.wicker.quotes.domain.QuotesRepository
import io.rcm.wicker.quotes.domain.model.QuoteEntity
import io.rcm.wicker.quotes.presentation.QuotesUiMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by joicemarinay on 27/06/2018.
 */
internal class GetQuotesUseCase @Inject constructor(private val repo: QuotesRepository,
  private val mapper: QuotesUiMapper): BaseUseCase<GetQuotes.Result>(), GetQuotes {

  override fun execute() {
    repo.getAll()
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(::success, ::error)
      .track()
  }

  private fun success(quotes: List<QuoteEntity>) {
    liveData.value = GetQuotes.Result.OnSuccess(quotes.map(mapper::mapFromDomain))
  }

  private fun error(throwable: Throwable) {
    Timber.e(throwable)
    liveData.value = GetQuotes.Result.OnError
  }

}