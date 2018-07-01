package io.rcm.wicker.quotes.presentation

import io.rcm.wicker.base.domain.EntityMapper
import io.rcm.wicker.quotes.domain.model.QuoteEntity
import javax.inject.Inject

/**
 * Created by joicemarinay on 6/30/18.
 *
 * Map a [QuoteUi] to and from a [QuoteEntity]
 * when data is transported from domain layer to presentation layer
 */
internal class QuotesUiMapper @Inject constructor(): EntityMapper<QuoteUi, QuoteEntity> {

  /**
   * Map a [QuoteUi] instance to a [QuoteEntity] instance
   */
  override fun mapToDomain(type: QuoteUi): QuoteEntity =
      QuoteEntity(id = type.id, quote = type.quote, author = type.author,
          sourceName = type.sourceName, sourceUrl = type.sourceUrl, isFavourite = type.isFavourite,
          isDeleted = type.isDeleted)

  /**
   * Map a [QuoteEntity] instance to a [QuoteUi] instance
   */
  override fun mapFromDomain(type: QuoteEntity): QuoteUi =
      QuoteUi(id = type.id, quote = type.quote, author = type.author,
          sourceName = type.sourceName, sourceUrl = type.sourceUrl, isFavourite = type.isFavourite,
          isDeleted = type.isDeleted)

  /**
   * Map a [QuoteUi] instance to a [QuoteEntity] instance
   */
  override fun mapToDomain(types: List<QuoteUi>): List<QuoteEntity> =
      types.map {
        QuoteEntity(id = it.id, quote = it.quote, author = it.author, sourceName = it.sourceName,
            sourceUrl = it.sourceUrl, isFavourite = it.isFavourite, isDeleted = it.isDeleted)
      }

  /**
   * Map a [QuoteEntity] instance to a [QuoteUi] instance
   */
  override fun mapFromDomain(types: List<QuoteEntity>): List<QuoteUi> =
      types.map {
        QuoteUi(id = it.id, quote = it.quote, author = it.author, sourceName = it.sourceName,
            sourceUrl = it.sourceUrl, isFavourite = it.isFavourite, isDeleted = it.isDeleted)
      }
}