package io.rcm.wicker.quotes.list.injection

import dagger.Component
import io.rcm.wicker.base.injection.components.BaseComponent
import io.rcm.wicker.quotes.domain.QuotesRepository
import io.rcm.wicker.quotes.injection.QuotesModule
import io.rcm.wicker.quotes.list.presentation.QuoteListActivity

/**
 * Created by joicemarinay on 6/24/18.
 */
@Component(
  dependencies = [BaseComponent::class],
  modules = [QuotesModule::class, QuoteListModule::class]
)
@QuoteListScope
internal interface QuoteListComponent {

  fun inject(quoteListActivity: QuoteListActivity)

  fun repository(): QuotesRepository
}