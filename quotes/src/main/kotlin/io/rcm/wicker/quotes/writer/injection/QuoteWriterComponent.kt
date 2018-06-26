package io.rcm.wicker.quotes.writer.injection

import dagger.Component
import io.rcm.wicker.quotes.list.injection.QuoteListComponent
import io.rcm.wicker.quotes.writer.presentation.QuoteWriterActivity

/**
 * Created by joicemarinay on 6/24/18.
 */
@Component(
  dependencies = [QuoteListComponent::class],
  modules = [QuoteWriterModule::class]
)
@QuoteWriterScope
internal interface QuoteWriterComponent {

  fun inject(quoteWriterActivity: QuoteWriterActivity)
}