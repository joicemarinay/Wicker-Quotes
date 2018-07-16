package io.rcm.wicker.quotes.features.details.injection

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.rcm.wicker.base.injection.keys.ViewModelKey
import io.rcm.wicker.quotes.features.details.domain.GetQuoteDetails
import io.rcm.wicker.quotes.features.details.domain.GetQuoteDetailsUseCase
import io.rcm.wicker.quotes.features.details.presentation.QuoteDetailsViewModel

/**
 * Created by joicemarinay on 7/1/18.
 */
@Module
internal abstract class QuoteDetailsModule {

  @Binds
  abstract fun bindGetQuoteDetails(getQuoteDetailsUseCase: GetQuoteDetailsUseCase): GetQuoteDetails

  @Binds
  @IntoMap
  @ViewModelKey(QuoteDetailsViewModel::class)
  abstract fun bindQuoteDetailsViewModel(quoteDetailsViewModel: QuoteDetailsViewModel): ViewModel

}