package io.rcm.wicker.quotes.data.local.db

import android.arch.persistence.room.*
import io.reactivex.Completable

/**
 * Created by joicemarinay on 20/04/2018.
 */
@Dao
internal interface QuotesDao {

  /**
   * Returns
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(quote: QuoteInDb)

}