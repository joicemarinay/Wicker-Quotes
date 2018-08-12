package io.rcm.wicker.quotes.features.list.injection

import dagger.Component
import io.rcm.wicker.base.analytics.AnalyticsTool
import io.rcm.wicker.base.injection.components.BaseComponent
import io.rcm.wicker.quotes.domain.QuotesRepository
import io.rcm.wicker.quotes.domain.usecase.DeleteQuote
import io.rcm.wicker.quotes.domain.usecase.ChangeDeleteState
import io.rcm.wicker.quotes.injection.QuotesModule
import io.rcm.wicker.quotes.features.list.presentation.QuoteListActivity
import io.rcm.wicker.quotes.presentation.ResourceProvider

/**
 * Created by joicemarinay on 6/24/18.
 */
@Component(
  dependencies = [BaseComponent::class],
  modules = [QuotesModule::class, QuoteListModule::class]
)
@QuoteListScope
internal interface QuoteListComponent {

  fun changeDeleteState(): ChangeDeleteState

  fun deleteQuoteUseCase(): DeleteQuote

  fun inject(quoteListActivity: QuoteListActivity)

  fun repository(): QuotesRepository

  fun resourceProvider(): ResourceProvider

  fun analyticsTool(): AnalyticsTool
}