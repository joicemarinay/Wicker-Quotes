package io.rcm.wicker.quotes.data.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import javax.inject.Singleton

/**
 * Created by joicemarinay on 6/24/18.
 */
@Database(version = 1, exportSchema = false,
    entities = [
      QuoteInDb::class
    ]
)
@Singleton
internal abstract class QuotesDb: RoomDatabase() {

  internal abstract fun quotesDao(): QuotesDao

  companion object {
    @Volatile
    private var INSTANCE: QuotesDb? = null

    private const val DB_NAME = "wicker-quotes.db"

    fun getInstance(context: Context): QuotesDb =
        INSTANCE ?: synchronized(this) {
          INSTANCE
              ?: buildDatabase(context)
              .also { INSTANCE = it }
        }

    private fun buildDatabase(context: Context) =
        Room.databaseBuilder(context.applicationContext, QuotesDb::class.java, DB_NAME).build()
  }

}