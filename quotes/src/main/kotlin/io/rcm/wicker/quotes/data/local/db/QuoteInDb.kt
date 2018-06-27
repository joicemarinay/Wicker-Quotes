package io.rcm.wicker.quotes.data.local.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 *
 * Model used solely for transacting quotes in and out of DB
 *
 * Created by joicemarinay on 18/04/2018.
 */
@Entity(tableName = QuoteInDb.TABLE_NAME)
internal data class QuoteInDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    val id: Int,

    @ColumnInfo(name = COLUMN_QUOTE)
    val quote: String,

    @ColumnInfo(name = COLUMN_AUTHOR)
    val author: String,

    @ColumnInfo(name = COLUMN_SOURCE_NAME)
    val sourceName: String,

    @ColumnInfo(name = COLUMN_SOURCE_URL)
    val sourceUrl: String,

    @ColumnInfo(name = COLUMN_IS_FAVOURITE)
    val isFavourite: Boolean = false,

    @ColumnInfo(name = COLUMN_IS_DELETED)
    val isDeleted: Boolean = false
//TODO add tags
) {
  companion object {
    const val TABLE_NAME = "quotes"
    const val COLUMN_ID = "quoteId"
    const val COLUMN_QUOTE = "quote"
    const val COLUMN_AUTHOR = "author"
    const val COLUMN_SOURCE_NAME = "sourceName"
    const val COLUMN_SOURCE_URL = "sourceUrl"
    const val COLUMN_IS_FAVOURITE = "isFavourite"
    const val COLUMN_IS_DELETED = "isDeleted"
  }
}