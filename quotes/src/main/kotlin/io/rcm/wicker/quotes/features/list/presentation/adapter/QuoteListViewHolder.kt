package io.rcm.wicker.quotes.features.list.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import io.rcm.wicker.quotes.presentation.QuoteUi
import kotlinx.android.synthetic.main.wicker_quote_list_item.view.*

/**
 * Created by joicemarinay on 6/30/18.
 */
internal class QuoteListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

  fun bind(quote: QuoteUi, listener: Listener) = with(itemView) {
    quoteListItem_text_authorAndSource.text = quote.authorAndSource
    quoteListItem_text_quote.text = quote.quote
    setOnClickListener{ listener.onQuoteClicked(quote) }
  }

  interface Listener {
    fun onQuoteClicked(quote: QuoteUi) //TODO check if item position should be passed as param
  }
}