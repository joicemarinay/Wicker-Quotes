package io.rcm.wicker.quotes.common

import io.rcm.wicker.quotes.domain.model.QuoteEntity
import io.rcm.wicker.quotes.presentation.QuoteUi
import java.util.*

/**
 * Created by joicemarinay on 30/07/2018.
 */
internal val quoteEntity: QuoteEntity by lazy { QuoteEntity(id = 5,
    quote = "It's always too early to quit.",
    author = "Norman Vincent Peale",
    sourceName = "",
    sourceUrl = "",
    isDeleted = false,
    isFavourite = false) }

internal val quoteEntityList: List<QuoteEntity> by lazy {
  ArrayList<QuoteEntity>(Collections.nCopies(5, quoteEntity)) }

internal val quoteUi: QuoteUi by lazy { QuoteUi(id = 5,
  quote = "It's always too early to quit.",
  author = "Norman Vincent Peale",
  sourceName = "",
  sourceUrl = "",
  isDeleted = false,
  isFavourite = false) }

internal val quoteUiList: List<QuoteUi> by lazy {
  ArrayList<QuoteUi>(Collections.nCopies(5, quoteUi)) }