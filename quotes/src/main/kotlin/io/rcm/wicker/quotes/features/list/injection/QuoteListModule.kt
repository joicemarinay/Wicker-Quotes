package io.rcm.wicker.quotes.features.list.injection

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.rcm.wicker.base.injection.keys.ViewModelKey
import io.rcm.wicker.quotes.features.list.domain.GetQuotes
import io.rcm.wicker.quotes.features.list.domain.GetQuotesUseCase
import io.rcm.wicker.quotes.features.list.presentation.QuoteListViewModel

/**
 * Created by joicemarinay on 6/24/18.
 */
@Module
internal abstract class QuoteListModule {

  @Binds
  abstract fun getQuotesUseCase(getQuotesUseCase: GetQuotesUseCase): GetQuotes

  @Binds
  @IntoMap
  @ViewModelKey(QuoteListViewModel::class)
  abstract fun quoteListViewModel(quoteListViewModel: QuoteListViewModel): ViewModel

}