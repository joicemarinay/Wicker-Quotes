package io.rcm.wicker.quotes.data.local

import io.rcm.wicker.quotes.data.local.db.QuotesDb
import io.rcm.wicker.quotes.domain.model.QuoteEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by joicemarinay on 20/04/2018.
 *
 * #RxNotes:
 * > [defer] operator
 *    - waits until an observer subscribes to it
 *    - creates a fresh [Observable] for each observer
 *
 * > [Flowable]
 *    - will emit data when there's change
 */
internal class QuotesLocalDataSource @Inject constructor(private val db: QuotesDb,
  private val entityMapper: QuotesLocalMapper): QuotesLocalSource {

  /**
   * Retrieve all [QuoteInDb] from quotes table in DB
   */
  override fun getAllQuotes(): Flowable<List<QuoteEntity>> =
    db.quotesDao().getAll().map(entityMapper::mapToDomain)

  /**
   * Save a Quote in local data source/store
   *
   * Returns [Completable] because we just want to be notified that the insert succeeded
   */
  override fun saveQuote(quote: QuoteEntity): Completable =
      Completable.defer {
        //TODO handle error when inserting
        val insertedId = db.quotesDao().insert(entityMapper.mapFromDomain(quote))
        Timber.d("saveQuote() $quote inserted with ID $insertedId")
        Completable.complete()
      }
}