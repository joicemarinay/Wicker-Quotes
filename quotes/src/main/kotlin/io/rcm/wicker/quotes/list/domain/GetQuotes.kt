package io.rcm.wicker.quotes.list.domain

import io.rcm.wicker.base.domain.UseCase
import io.rcm.wicker.quotes.presentation.QuoteUi

/**
 * Created by joicemarinay on 27/06/2018.
 */
internal interface GetQuotes: UseCase<GetQuotes.Result> {

  sealed class Result {
    data class OnSuccess(val quotes: List<QuoteUi>) : Result()
    object OnError : Result()
  }

  fun execute()
}