package io.rcm.wicker.quotes.domain

import io.rcm.wicker.base.common.Either
import io.rcm.wicker.base.common.Failure
import io.rcm.wicker.quotes.domain.model.QuoteEntity
import io.reactivex.Completable

/**
 * Created by joicemarinay on 6/24/18.
 */
internal interface QuotesRepository {

  //TODO change to Either<Failure, QuoteEntity>
  fun save(quote: QuoteEntity): Completable
}