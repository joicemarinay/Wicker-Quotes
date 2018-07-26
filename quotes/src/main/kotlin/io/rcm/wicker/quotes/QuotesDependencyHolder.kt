package io.rcm.wicker.quotes

import io.rcm.wicker.base.WickerApp
import io.rcm.wicker.quotes.features.details.injection.DaggerQuoteDetailsComponent
import io.rcm.wicker.quotes.features.details.injection.QuoteDetailsComponent
import io.rcm.wicker.quotes.features.list.injection.DaggerQuoteListComponent
import io.rcm.wicker.quotes.features.list.injection.QuoteListComponent
import io.rcm.wicker.quotes.features.receiver.injection.DaggerQuoteReceiverComponent
import io.rcm.wicker.quotes.features.receiver.injection.QuoteReceiverComponent
import io.rcm.wicker.quotes.features.writer.injection.DaggerQuoteWriterComponent
import io.rcm.wicker.quotes.features.writer.injection.QuoteWriterComponent
import javax.inject.Singleton

/**
 * Created by joicemarinay on 6/24/18.
 *
 * Adapted from:
 * https://github.com/karntrehan/Posts/blob/master/posts/src/main/java/com/karntrehan/posts/commons/PostDH.kt
 */
@Singleton
internal object QuotesDependencyHolder {

  private var quoteDetailsComponent: QuoteDetailsComponent? = null
  private var quoteListComponent: QuoteListComponent? = null
  private var quoteReceiverComponent: QuoteReceiverComponent? = null
  private var quoteWriterComponent: QuoteWriterComponent? = null
  
  fun detailsComponent(): QuoteDetailsComponent {
    if (quoteDetailsComponent == null)
      quoteDetailsComponent = DaggerQuoteDetailsComponent.builder()
          .quoteListComponent(listComponent()).build()
    return quoteDetailsComponent as QuoteDetailsComponent
  }

  fun destroyDetailsComponent() {
    quoteDetailsComponent = null
  }
  
  fun listComponent(): QuoteListComponent {
    if (quoteListComponent == null)
      quoteListComponent = DaggerQuoteListComponent.builder()
          .baseComponent(WickerApp.baseComponent).build()
    return quoteListComponent as QuoteListComponent
  }

  fun destroyListComponent() {
    quoteListComponent = null
  }

  fun receiverComponent(): QuoteReceiverComponent {
    if (quoteReceiverComponent == null)
      quoteReceiverComponent = DaggerQuoteReceiverComponent.builder()
        .quoteListComponent(listComponent()).build()
    return quoteReceiverComponent as QuoteReceiverComponent
  }

  fun destroyReceiverComponent() {
    quoteReceiverComponent = null
  }

  fun writerComponent(): QuoteWriterComponent {
    if (quoteWriterComponent == null)
      quoteWriterComponent = DaggerQuoteWriterComponent.builder()
        .quoteListComponent(listComponent()).build()
    return quoteWriterComponent as QuoteWriterComponent
  }

  fun destroyWriterComponent() {
    quoteWriterComponent = null
  }
}