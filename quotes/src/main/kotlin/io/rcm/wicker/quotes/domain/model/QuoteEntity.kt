package io.rcm.wicker.quotes.domain.model

/**
 * Representation of a [QuoteEntity] fetched from an external layer data source
 *
 * Created by joicemarinay on 20/04/2018.
 */
data class QuoteEntity(
    val quote: String,
    val author: String,
    val sourceName: String,
    val sourceUrl: String,
    val isFavourite: Boolean = false,
    val isDeleted: Boolean = false,
    val id: Int = 0
//TODO add tags
)