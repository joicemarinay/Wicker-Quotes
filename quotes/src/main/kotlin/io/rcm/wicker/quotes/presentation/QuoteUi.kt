package io.rcm.wicker.quotes.presentation

/**
 * Created by joicemarinay on 6/30/18.
 */
internal data class QuoteUi(
    val id: Int = 0,
    val quote: String,
    val author: String,
    val sourceName: String,
    val sourceUrl: String,
    val isFavourite: Boolean = false,
    val isDeleted: Boolean = false
    //TODO add tags
) {

  val dashedAuthorAndSource: String get() = when {
    authorAndSource.isNotEmpty() -> "– $authorAndSource"
    else -> ""
  }

  val authorAndSource: String get() = when {
    author.isEmpty() && sourceName.isEmpty() -> ""
    author.isEmpty() && sourceName.isNotEmpty() -> sourceName
    author.isNotEmpty() && sourceName.isEmpty() -> author
    author.isNotEmpty() && sourceName.isNotEmpty() -> "$author, $sourceName"
    else -> ""
  }
}