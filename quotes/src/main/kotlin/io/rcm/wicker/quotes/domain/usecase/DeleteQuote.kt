package io.rcm.wicker.quotes.domain.usecase

import io.rcm.wicker.base.domain.UseCase

/**
 * Created by joicemarinay on 17/07/2018.
 */
internal interface DeleteQuote: UseCase<DeleteQuote.Result> {

  sealed class Result {
    object OnSuccess: Result()
    object OnError: Result()
  }

  fun execute(quoteId: Int)
}