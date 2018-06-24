package io.rcm.wicker.quotes.list.injection

import dagger.Component
import io.rcm.wicker.base.injection.components.BaseComponent
import io.rcm.wicker.quotes.list.presentation.QuoteListActivity

/**
 * Created by joicemarinay on 6/24/18.
 */
@Component(
    dependencies = [BaseComponent::class],
    modules = [QuoteListModule::class]
)
@QuoteListScope
internal interface QuoteListComponent {

  fun inject(quoteListActivity: QuoteListActivity)
}