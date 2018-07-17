package io.rcm.wicker.quotes.data.local

import io.rcm.wicker.base.domain.EntityMapper
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
  override fun mapToDomain(type: QuoteInDb): QuoteEntity =
      QuoteEntity(id = type.id, quote = type.quote, author = type.author,
        sourceName = type.sourceName, sourceUrl = type.sourceUrl, isFavourite = type.isFavourite,
        isDeleted = type.isSoftDeleted)

  /**
   * Map a [QuoteEntity] instance to a [QuoteInDb] instance
   */
  override fun mapFromDomain(type: QuoteEntity): QuoteInDb =
      QuoteInDb(id = type.id, quote = type.quote, author = type.author,
        sourceName = type.sourceName, sourceUrl = type.sourceUrl, isFavourite = type.isFavourite,
        isSoftDeleted = type.isDeleted)

  /**
   * Map a [QuoteInDb] instance to a [QuoteEntity] instance
   */
  override fun mapToDomain(types: List<QuoteInDb>): List<QuoteEntity> =
      types.map {
      QuoteEntity(id = it.id, quote = it.quote, author = it.author, sourceName = it.sourceName,
        sourceUrl = it.sourceUrl, isFavourite = it.isFavourite, isDeleted = it.isSoftDeleted)
    }

  /**
   * Map a [QuoteEntity] instance to a [QuoteInDb] instance
   */
  override fun mapFromDomain(types: List<QuoteEntity>): List<QuoteInDb> =
      types.map {
      QuoteInDb(id = it.id, quote = it.quote, author = it.author, sourceName = it.sourceName,
        sourceUrl = it.sourceUrl, isFavourite = it.isFavourite, isSoftDeleted = it.isDeleted)
    }
}