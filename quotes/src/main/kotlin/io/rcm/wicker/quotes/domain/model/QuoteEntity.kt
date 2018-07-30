package io.rcm.wicker.quotes.domain.model

import io.rcm.wicker.base.domain.BaseEntity

/**
 * Created by joicemarinay on 20/04/2018.
 *
 * Representation of a [QuoteEntity] fetched either from an external layer data source or
 *  will be written to external layer data source
 */
internal data class QuoteEntity(
  override val id: Int = 0,
  val quote: String,
  val author: String,
  val sourceName: String,
  val sourceUrl: String,
  val isFavourite: Boolean = false,
  val isDeleted: Boolean = false
  //TODO add tags
): BaseEntity