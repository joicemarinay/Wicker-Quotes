package io.rcm.wicker.quotes.features.writer.domain

import io.rcm.wicker.base.domain.UseCase
import io.rcm.wicker.quotes.domain.model.QuoteEntity

/**
 * Created by joicemarinay on 18/05/2018.
 */
internal interface SaveQuote: UseCase<SaveQuote.Result> {

  /**
   * #KotlinNotes
   * This is sealed because it indicates that there won't be any subclasses
   *  of [Result] other than the ones defined here.
   *  Pros:
   *  - No need to handle `else` (default branch) in a `when` expression
   */
  sealed class Result {
    object OnSuccess: Result()
    object OnError: Result()
  }

  fun execute(quote: QuoteEntity)

}