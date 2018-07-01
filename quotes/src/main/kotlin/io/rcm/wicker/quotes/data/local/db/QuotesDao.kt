package io.rcm.wicker.quotes.data.local.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

/**
 * Created by joicemarinay on 20/04/2018.
 */
@Dao
internal interface QuotesDao {

  @Query("SELECT * FROM ${QuoteInDb.TABLE_NAME}")
  fun getAll(): Flowable<List<QuoteInDb>>

  /**
   * Returns row ID of the last row inserted if insert is successful
   *  Returns -1 if this insert fails
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(quote: QuoteInDb): Long

}