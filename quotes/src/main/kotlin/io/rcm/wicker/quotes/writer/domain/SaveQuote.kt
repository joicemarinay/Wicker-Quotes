package io.rcm.wicker.quotes.writer.domain

import io.rcm.wicker.base.domain.UseCase
import io.rcm.wicker.quotes.domain.model.QuoteEntity

/**
 * Created by joicemarinay on 18/05/2018.
 */
internal interface SaveQuote: UseCase<SaveQuote.Result> {

  //STUDY why does this have to be sealed?
  sealed class Result {
    object OnSuccess: Result()
    object OnError: Result()
  }

  fun execute(quote: QuoteEntity)

}