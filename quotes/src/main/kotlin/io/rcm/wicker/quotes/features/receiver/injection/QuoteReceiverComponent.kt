package io.rcm.wicker.quotes.features.receiver.injection

import dagger.Component
import io.rcm.wicker.quotes.features.list.injection.QuoteListComponent
import io.rcm.wicker.quotes.features.receiver.presentation.QuoteReceiverActivity
import io.rcm.wicker.quotes.features.writer.injection.QuoteWriterModule

/**
 * Created by joicemarinay on 22/07/2018.
 */
@Component(
  dependencies = [QuoteListComponent::class],
  modules = [QuoteWriterModule::class]
)
@QuoteReceiverScope
internal interface QuoteReceiverComponent {

  fun inject(quoteReceiverActivity: QuoteReceiverActivity)
}