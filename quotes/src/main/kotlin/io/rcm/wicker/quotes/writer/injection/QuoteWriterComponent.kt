package io.rcm.wicker.quotes.writer.injection

import dagger.Component
import io.rcm.wicker.base.injection.components.BaseComponent
import io.rcm.wicker.quotes.writer.presentation.QuoteWriterActivity

/**
 * Created by joicemarinay on 6/24/18.
 */
@Component(
    dependencies = [BaseComponent::class],
    modules = [QuoteWriterModule::class]
)
@QuoteWriterScope
internal interface QuoteWriterComponent {

  fun inject(quoteWriterActivity: QuoteWriterActivity)
}