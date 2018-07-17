package io.rcm.wicker.quotes.domain.usecase

import io.rcm.wicker.base.domain.BaseUseCase
import io.rcm.wicker.quotes.domain.QuotesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by joicemarinay on 17/07/2018.
 */
internal class DeleteQuoteUseCase @Inject constructor(private val repo: QuotesRepository):
  BaseUseCase<DeleteQuote.Result>(), DeleteQuote {

  override fun execute(quoteId: Int) {
    repo.deleteQuote(quoteId)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(::success, ::error)
      .track()
  }

  private fun success() {
    liveData.value = DeleteQuote.Result.OnSuccess
  }

  private fun error(throwable: Throwable) {
    Timber.e(throwable)
    liveData.value = DeleteQuote.Result.OnError
  }
}