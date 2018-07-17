package io.rcm.wicker.quotes.domain

import io.rcm.wicker.quotes.domain.model.QuoteEntity
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by joicemarinay on 6/24/18.
 *
 * Interface defining methods for how the data layer can access [QuoteEntity]s
 * to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */
internal interface QuotesRepository {

  fun deleteQuote(id: Int): Completable

  fun getAll(): Flowable<List<QuoteEntity>>

  fun getQuote(id: Int): Flowable<QuoteEntity>

  fun save(quote: QuoteEntity): Completable
}