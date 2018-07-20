package io.rcm.wicker.quotes.data.local.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import io.reactivex.Flowable

/**
 * Created by joicemarinay on 20/04/2018.
 */
@Dao
internal interface QuotesDao {

  /**
   * Delete a quote by id.
   *
   * @return the number of quotes deleted. This should always be 1.
   */
  @Query("DELETE FROM ${QuoteInDb.TABLE_NAME} WHERE ${QuoteInDb.COLUMN_ID} = :id")
  fun deleteQuoteById(id: Int): Int

  @Query("SELECT * FROM ${QuoteInDb.TABLE_NAME} " +
    "WHERE ${QuoteInDb.COLUMN_IS_SOFT_DELETED} = 0 ORDER BY ${QuoteInDb.COLUMN_ID} DESC")
  fun getAll(): Flowable<List<QuoteInDb>>

  @Query("SELECT * FROM ${QuoteInDb.TABLE_NAME} WHERE ${QuoteInDb.COLUMN_ID} = :id")
  fun getQuote(id: Int): Flowable<QuoteInDb>

  /**
   * Returns row ID of the last row inserted if insert is successful
   *  Returns -1 if this insert fails
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(quote: QuoteInDb): Long

  /**
   * This will be used by:
   * - soft deleting quote (set isSoftDeleted to true, equivalent to 1 in DB)
   * - undo (soft) deleting of quote (set isSoftDeleted to false, equivalent to 0 in DB)
   *
   * @return the number of quotes soft-deleted (updated). This should always be 1.
   */
  @Update
  fun update(quote: QuoteInDb): Int

}