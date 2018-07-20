package io.rcm.wicker.quotes.domain.usecase

import io.rcm.wicker.base.domain.BaseUseCase
import io.rcm.wicker.quotes.domain.QuotesRepository
import io.rcm.wicker.quotes.presentation.QuoteUi
import io.rcm.wicker.quotes.presentation.QuotesUiMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by joicemarinay on 17/07/2018.
 */
internal class ChangeDeleteUseCase @Inject constructor(private val mapper: QuotesUiMapper,
  private val repo: QuotesRepository): BaseUseCase<ChangeDeleteState.Result>(), ChangeDeleteState {

  override fun execute(quote: QuoteUi, isSoftDeleted: Boolean) {
    quote.isDeleted = isSoftDeleted
    repo.updateQuote(mapper.mapToDomain(quote))
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(::success, ::error)
      .track()
  }

  private fun success() {
    liveData.value = ChangeDeleteState.Result.OnSuccess
  }

  private fun error(throwable: Throwable) {
    Timber.e(throwable)
    liveData.value = ChangeDeleteState.Result.OnError
  }
}