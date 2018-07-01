package io.rcm.wicker.quotes.features.list.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.rcm.wicker.base.common.inflate
import io.rcm.wicker.quotes.R
import io.rcm.wicker.quotes.presentation.QuoteUi

/**
 * Created by joicemarinay on 6/30/18.
 */
internal class QuoteListAdapter(private val listener: QuoteListViewHolder.Listener):
    RecyclerView.Adapter<QuoteListViewHolder>() {

  private var quoteList: List<QuoteUi> = emptyList()

  override fun onBindViewHolder(holder: QuoteListViewHolder, position: Int) =
      holder.bind(quoteList[position], listener)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteListViewHolder
      = QuoteListViewHolder(parent.inflate(R.layout.wicker_quote_list_item))

  override fun getItemCount(): Int = quoteList.size

  fun setQuoteList(quoteList: List<QuoteUi>?) {
    this.quoteList = quoteList ?: this.quoteList
    notifyDataSetChanged()

  }

}