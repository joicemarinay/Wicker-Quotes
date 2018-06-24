package io.rcm.wicker.quotes.data.local

import io.rcm.wicker.base.data.EntityMapper
import io.rcm.wicker.quotes.data.local.db.QuoteInDb
import io.rcm.wicker.quotes.domain.model.QuoteEntity
import javax.inject.Inject

/**
 * Created by joicemarinay on 20/04/2018.
 *
 * Map a [QuoteInDb] instance to and from a [QuoteEntity] instance
 * when data moves between the local data layer and the outer data layer.
 */
internal class QuotesLocalMapper @Inject constructor(): EntityMapper<QuoteInDb, QuoteEntity> {

  /**
   * Map a [QuoteInDb] instance to a [QuoteEntity] instance
   */
  override fun mapFromLocal(type: QuoteInDb): QuoteEntity =
      QuoteEntity(type.quote, type.author, type.sourceName, type.sourceUrl,
        type.isFavourite, type.isDeleted, type.id)

  /**
   * Map a [QuoteEntity] instance to a [QuoteInDb] instance
   */
  override fun mapToLocal(type: QuoteEntity): QuoteInDb =
      QuoteInDb(type.id, type.quote, type.author, type.sourceName, type.sourceUrl,
        type.isFavourite, type.isDeleted)

  /**
   * Map a [QuoteInDb] instance to a [QuoteEntity] instance
   */
  override fun mapFromLocal(types: List<QuoteInDb>): List<QuoteEntity> =
      types.map {
      QuoteEntity(it.quote, it.author, it.sourceName, it.sourceUrl,
          it.isFavourite, it.isDeleted, it.id)
    }

  /**
   * Map a [QuoteEntity] instance to a [QuoteInDb] instance
   */
  override fun mapToLocal(types: List<QuoteEntity>): List<QuoteInDb> =
      types.map {
      QuoteInDb(it.id, it.quote, it.author, it.sourceName, it.sourceUrl, it.isFavourite,
          it.isDeleted)
    }
}