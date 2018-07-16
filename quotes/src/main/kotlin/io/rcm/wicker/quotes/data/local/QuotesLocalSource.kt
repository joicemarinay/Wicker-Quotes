package io.rcm.wicker.quotes.data.local

import io.rcm.wicker.quotes.domain.model.QuoteEntity
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by joicemarinay on 20/04/2018.
 *
 * Interface defining methods for saving Quotes locally (DB).
 * This is to be implemented by the local data source layer,
 * using this interface as a way of communicating with domain layer.
 */
internal interface QuotesLocalSource {

  fun getAllQuotes(): Flowable<List<QuoteEntity>>

  fun getQuote(id: Int): Flowable<QuoteEntity>

  fun saveQuote(quote: QuoteEntity): Completable
}