package io.rcm.wicker.quotes.writer.domain

import io.rcm.wicker.base.domain.BaseUseCase
import io.rcm.wicker.quotes.domain.QuotesRepository
import io.rcm.wicker.quotes.domain.model.QuoteEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by joicemarinay on 18/05/2018.
 */
internal class SaveQuoteUseCase @Inject constructor(private val repo: QuotesRepository):
    BaseUseCase<SaveQuote.Result>(), SaveQuote {

  override fun execute(quote: QuoteEntity) {
    repo.save(quote)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(::success, ::error)
        .track()
  }

  private fun success() {
    liveData.value = SaveQuote.Result.OnSuccess
  }

  private fun error(throwable: Throwable) {
    Timber.e(throwable)
    liveData.value = SaveQuote.Result.OnError
  }
}