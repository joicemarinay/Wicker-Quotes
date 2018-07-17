package io.rcm.wicker.quotes.presentation

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Parcel
import io.rcm.wicker.base.common.KParcelable
import io.rcm.wicker.base.common.parcelableCreator
import io.rcm.wicker.base.common.readBoolean
import io.rcm.wicker.base.common.writeBoolean

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
    var isDeleted: Boolean = false
    //TODO add tags
): KParcelable {

  constructor(parcel: Parcel): this(id = parcel.readInt(), quote = parcel.readString(),
      author = parcel.readString(), sourceName = parcel.readString(),
      sourceUrl = parcel.readString(), isFavourite = parcel.readBoolean(),
      isDeleted = parcel.readBoolean())

  val dashedAuthorAndSource: String get() = when {
    authorAndSource.isNotEmpty() -> "–$authorAndSource"
    else -> ""
  }

  val authorAndSource: String get() = when {
    author.isEmpty() && sourceName.isEmpty() -> ""
    author.isEmpty() && sourceName.isNotEmpty() -> sourceName
    author.isNotEmpty() && sourceName.isEmpty() -> author
    author.isNotEmpty() && sourceName.isNotEmpty() -> "$author, $sourceName"
    else -> ""
  }

  fun formattedQuote(resourceProvider: ResourceProvider): String =
    "${resourceProvider.openQuoteMark()}$quote${resourceProvider.closeQuoteMark()}" +
      "\n$dashedAuthorAndSource"

  override fun writeToParcel(dest: Parcel, flags: Int) {
    with(dest) {
      writeInt(id)
      writeString(quote)
      writeString(author)
      writeString(sourceName)
      writeString(sourceUrl)
      writeBoolean(isFavourite)
      writeBoolean(isDeleted)
    }
  }

  companion object {
    @JvmField val CREATOR = parcelableCreator(::QuoteUi)

    fun empty() =  QuoteUi(quote = "", author = "", sourceName = "", sourceUrl = "")
  }
}

//<editor-fold desc="Extension Functions">
//TODO check if this is safe
internal fun QuoteUi.copyToClipBoard(context: Context, resourceProvider: ResourceProvider) {
  val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
  clipboard.primaryClip = ClipData.newPlainText(resourceProvider.appName(),
    this.formattedQuote(resourceProvider))
}
//</editor-fold>