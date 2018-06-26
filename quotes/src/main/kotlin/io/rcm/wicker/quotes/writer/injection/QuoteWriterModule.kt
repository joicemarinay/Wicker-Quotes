package io.rcm.wicker.quotes.writer.injection

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.rcm.wicker.base.injection.keys.ViewModelKey
import io.rcm.wicker.quotes.writer.domain.SaveQuote
import io.rcm.wicker.quotes.writer.domain.SaveQuoteUseCase
import io.rcm.wicker.quotes.writer.presentation.QuoteWriterViewModel

/**
 * Created by joicemarinay on 6/24/18.
 */
@Module
internal abstract class QuoteWriterModule {

  @Binds
  @IntoMap
  @ViewModelKey(QuoteWriterViewModel::class)
  abstract fun bindQuoteWriterViewModel(quoteWriterViewModel: QuoteWriterViewModel): ViewModel

  @Binds
  abstract fun bindSaveQuoteUseCase(saveQuoteUseCase: SaveQuoteUseCase): SaveQuote
}