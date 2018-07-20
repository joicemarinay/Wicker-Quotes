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
 *    - Why Flowable? (answer found here https://tkolbusz.github.io/controlling-database-flow/)
 *      > "Google Developers had in mind that SQLite databases are updated in much shorter
 *      periods of time than Android UI is. Flowable adds option to handle backpressure,
 *      in short: when there are too many database updates to handle,
 *      only most recent update will be emitted through our reactive stream."
 */
internal class QuotesLocalDataSource @Inject constructor(private val db: QuotesDb,
  private val entityMapper: QuotesLocalMapper): QuotesLocalSource {

  /**
   * Delete a Quote in local data source/store using its ID
   *
   * Returns [Completable] because we notify that deletion succeeded
   */
  override fun deleteQuote(id: Int): Completable =
    Completable.defer {
      //TODO handle error when inserting
      val deletedQuoteCount = db.quotesDao().deleteQuoteById(id)
      Timber.d("deleteQuote() is running on ${Thread.currentThread()}")
      Timber.d("deleteQuote() number of deleted quotes is $deletedQuoteCount")
      Completable.complete()
    }

  /**
   * Retrieve all [QuoteInDb] from quotes table in DB
   */
  override fun getAllQuotes(): Flowable<List<QuoteEntity>> {
    Timber.d("getAllQuotes() is running on ${Thread.currentThread()}")
    return db.quotesDao().getAll().map(entityMapper::mapToDomain)
  }

  /**
   * Retrieve [QuoteInDb] from quotes table in DB
   *
   * @param id: ID of quote to retrieve
   */
  override fun getQuote(id: Int): Flowable<QuoteEntity> {
    Timber.d("getQuote() is running on ${Thread.currentThread()}")
    return db.quotesDao().getQuote(id).map(entityMapper::mapToDomain)
  }

  /**
   * Save a Quote in local data source/store
   *
   * Returns [Completable] because we just want to be notified that the insert succeeded
   */
  override fun saveQuote(quote: QuoteEntity): Completable =
      Completable.defer {
        //TODO handle error when inserting
        val insertedId = db.quotesDao().insert(entityMapper.mapFromDomain(quote))
        Timber.d("saveQuote() is running on ${Thread.currentThread()}")
        Timber.d("saveQuote() $quote inserted with ID $insertedId")
        Completable.complete()
      }

  override fun updateQuote(quote: QuoteEntity): Completable =
    Completable.defer {
      Timber.d("updateQuote() $quote")
      val softDeletedQuote = db.quotesDao().update(entityMapper.mapFromDomain(quote))
      Timber.d("updateQuote() is running on ${Thread.currentThread()}")
      Timber.d("updateQuote() number of soft-deleted quote is $softDeletedQuote")
      Completable.complete()
    }
}