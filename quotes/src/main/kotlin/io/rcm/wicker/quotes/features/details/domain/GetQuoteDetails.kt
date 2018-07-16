package io.rcm.wicker.quotes.features.details.domain

import io.rcm.wicker.base.domain.UseCase
import io.rcm.wicker.quotes.presentation.QuoteUi

/**
 * Created by joicemarinay on 15/07/2018.
 */
internal interface GetQuoteDetails: UseCase<GetQuoteDetails.Result> {

  sealed class Result {
    data class OnSuccess(val quote: QuoteUi) : Result()
    object OnError : Result()
  }

  fun execute(quoteId: Int)
}