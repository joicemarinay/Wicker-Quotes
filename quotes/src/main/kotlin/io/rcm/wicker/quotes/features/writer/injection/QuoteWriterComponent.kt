package io.rcm.wicker.quotes.features.writer.injection

import dagger.Component
import io.rcm.wicker.quotes.features.list.injection.QuoteListComponent
import io.rcm.wicker.quotes.features.writer.presentation.QuoteWriterActivity

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