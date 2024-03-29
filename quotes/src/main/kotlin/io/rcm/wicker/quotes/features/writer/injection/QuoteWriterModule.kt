package io.rcm.wicker.quotes.features.writer.injection

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.rcm.wicker.base.injection.keys.ViewModelKey
import io.rcm.wicker.quotes.features.writer.domain.SaveQuote
import io.rcm.wicker.quotes.features.writer.domain.SaveQuoteUseCase
import io.rcm.wicker.quotes.features.writer.presentation.QuoteWriterState
import io.rcm.wicker.quotes.features.writer.presentation.QuoteWriterViewModel

/**
 * Created by joicemarinay on 6/24/18.
 */
@Module(includes = [
  QuoteWriterModule.Companion::class
])
internal abstract class QuoteWriterModule {

  @Binds
  @IntoMap
  @ViewModelKey(QuoteWriterViewModel::class)
  abstract fun quoteWriterViewModel(quoteWriterViewModel: QuoteWriterViewModel): ViewModel

  @Binds
  abstract fun saveQuoteUseCase(saveQuoteUseCase: SaveQuoteUseCase): SaveQuote

  @Module
  companion object {

    @Provides
    fun uiState(): MediatorLiveData<QuoteWriterState> = MediatorLiveData()

  }
}