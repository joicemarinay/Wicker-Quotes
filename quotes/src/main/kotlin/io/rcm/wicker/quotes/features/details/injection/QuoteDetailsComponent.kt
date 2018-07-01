package io.rcm.wicker.quotes.features.details.injection

import dagger.Component
import io.rcm.wicker.quotes.features.details.presentation.QuoteDetailsActivity
import io.rcm.wicker.quotes.features.list.injection.QuoteListComponent

/**
 * Created by joicemarinay on 7/1/18.
 */
@Component(
  dependencies = [QuoteListComponent::class],
  modules = [QuoteDetailsModule::class]
)
@QuoteDetailsScope
internal interface QuoteDetailsComponent {

  fun inject(quoteDetailsActivity: QuoteDetailsActivity)
}