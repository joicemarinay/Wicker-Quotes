package io.rcm.wicker.quotes

import io.rcm.wicker.base.WickerApp
import io.rcm.wicker.quotes.list.injection.DaggerQuoteListComponent
import io.rcm.wicker.quotes.list.injection.QuoteListComponent
import io.rcm.wicker.quotes.writer.injection.DaggerQuoteWriterComponent
import io.rcm.wicker.quotes.writer.injection.QuoteWriterComponent
import javax.inject.Singleton

/**
 * Created by joicemarinay on 6/24/18.
 *
 * Adapted from:
 * https://github.com/karntrehan/Posts/blob/master/posts/src/main/java/com/karntrehan/posts/commons/PostDH.kt
 */
@Singleton
internal object QuotesDependencyHolder {

  private var quoteListComponent: QuoteListComponent? = null
  private var quoteWriterComponent: QuoteWriterComponent? = null

  fun listComponent(): QuoteListComponent {
    if (quoteListComponent == null)
      quoteListComponent = DaggerQuoteListComponent.builder()
          .baseComponent(WickerApp.baseComponent).build()
    return quoteListComponent as QuoteListComponent
  }

  fun destroyListComponent() {
    quoteListComponent = null
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